# ADR 0001: Kotoba is the VDA catalog source authority

- Status: Accepted
- Date: 2026-07-21

## Decision

`src/association_facts.kotoba` is the sole production source. Both citations
retain every present scalar field. The first citation continues to omit an
unverified date, while the second retains the year-only `1901` established
date. Topic count plus indexed access preserves the ordered human-rights and
supply-chain pair and the governance singleton. Unknown values and indexes
return zero or typed option-none; no effects are declared.

CI executes reference semantics, restricted JavaScript, instantiated typed
WebAssembly, and production source-authority checks. Clojure and the JVM are
compiler/test hosts only.

## Consequences

- Missing dates remain absent instead of being guessed.
- Multi-topic entries remain complete without host sets.
