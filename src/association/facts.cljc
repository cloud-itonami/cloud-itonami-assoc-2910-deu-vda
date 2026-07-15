(ns association.facts
  "Industry self-regulatory rule catalog for the Verband der
  Automobilindustrie (VDA, German Association of the Automotive
  Industry) -- a 17th industry-association-level source (see
  cloud-itonami-assoc-6419-jpn-zenginkyo, -6512-jpn-sonpo, -6612-jpn-jsda,
  -6419-deu-bankenverband, -6612-usa-finra, -6512-usa-naic,
  -6920-jpn-jicpa, -6920-usa-aicpa, -6419-fra-fbf, -6511-jpn-seiho,
  -6910-jpn-nichibenren, -6810-jpn-recaj, -6411-jpn-boj, -6120-usa-ctia,
  -5110-usa-a4a, -3510-usa-eei for the first sixteen) per ADR-2607141700
  (cloud-itonami-compliance-fact-federation). The FIRST entry aligned to
  ISIC 2910 (manufacture of motor vehicles) -- a new industry code for
  this family. A rule not in this table has NO spec-basis, full stop;
  extend `catalog`, do not invent an id/url.

  The Code of Conduct für Geschäftspartner (Code of Conduct for Business
  Partners) PDF -- jointly published with CLEPA (the European
  Association of Automotive Suppliers) and aligned with the Responsible
  Business Alliance Code of Conduct and the German Supply Chain Due
  Diligence Act -- was verified by directly reading its rendered cover
  page via the Read tool (no exact publication date was legible on that
  page, so `:established-date`/`:last-revised-date` are deliberately
  omitted rather than guessed). The About VDA page was directly
  WebFetch-verified; VDA's 1901 founding is corroborated via WebSearch
  and the association's own '125 Jahre VDA' (125 Years VDA) reference on
  its Organization page, not a single primary-page date statement.")

(def catalog
  "assoc-slug -> vector of self-regulatory rule entries."
  {"vda"
   [{:association-rule/id "vda.code-of-conduct-business-partners"
     :association-rule/title "Code of Conduct für Geschäftspartner (Code of Conduct for Business Partners), jointly with CLEPA"
     :association-rule/association "vda"
     :association-rule/isic "2910"
     :association-rule/country "DEU"
     :association-rule/kind :self-regulatory-code
     :association-rule/url "https://www.vda.de/dam/jcr:4c19640b-b6fc-44bd-b633-01a2d317fc49/VDA-Leitfaden_Code-of-Conduct_Empfehlung.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/retrieved-at "2026-07-15"
     :association-rule/topic #{:human-rights :supply-chain}}
    {:association-rule/id "vda.about-organization"
     :association-rule/title "About VDA (organization profile)"
     :association-rule/association "vda"
     :association-rule/isic "2910"
     :association-rule/country "DEU"
     :association-rule/kind :governance-program
     :association-rule/url "https://www.vda.de/en/association/about"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "1901"
     :association-rule/retrieved-at "2026-07-15"
     :association-rule/topic #{:governance}}]})

(defn spec-basis [assoc-slug] (get catalog assoc-slug))

(defn coverage
  ([] (coverage (keys catalog)))
  ([slugs]
   (let [have (filter catalog slugs)
         missing (remove catalog slugs)]
     {:requested (count slugs)
      :covered (count have)
      :covered-associations (vec (sort have))
      :missing-associations (vec (sort missing))
      :note (str "cloud-itonami-assoc-2910-deu-vda Wave 0 (ADR-2607141700): "
                 (count (get catalog "vda")) " vda entries seeded with an "
                 "official vda.de citation. Extend "
                 "`association.facts/catalog`, never fabricate a rule id/url.")})))

(defn by-topic [assoc-slug topic]
  (filterv #(contains? (:association-rule/topic %) topic) (spec-basis assoc-slug)))
