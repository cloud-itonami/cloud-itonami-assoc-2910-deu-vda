(ns association.facts-test
  (:require [clojure.test :refer [deftest is]]
            [association.facts :as facts]))

(deftest vda-has-spec-basis
  (let [sb (facts/spec-basis "vda")]
    (is (= 2 (count sb)))
    (is (every? #(= "2910" (:association-rule/isic %)) sb))
    (is (every? #(= "DEU" (:association-rule/country %)) sb))))

(deftest unknown-association-has-no-spec-basis
  (is (nil? (facts/spec-basis "clepa")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["vda" "clepa"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["clepa"] (:missing-associations c)))))

(deftest by-topic-filters
  (is (= ["vda.code-of-conduct-business-partners"]
         (mapv :association-rule/id (facts/by-topic "vda" :supply-chain))))
  (is (empty? (facts/by-topic "vda" :labor)))
  (is (empty? (facts/by-topic "clepa" :governance))))
