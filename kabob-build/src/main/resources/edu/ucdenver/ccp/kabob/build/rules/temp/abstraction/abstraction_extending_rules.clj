;;;; Rules to generate bio constructs from drugbank targets

;; obo/INO_0000002 ;interaction
;; obo/MI_0000 ;psi-mi "molecular interaction"

;;TODO the restrictions need to be properly reified

`{:name "connect-GP-to-GorGP-abstraction"
  ;; this need to be hashed and have proper classes
  :head ((?/geneProduct rdfs/subClassOf ?/gorgp))
  
  :body
  ((?/gene rdf/type kbio/GeneSpecificGClass)
   (?/gene rdfs/subClassOf ?/gorgp)
   (?/gorgp rdf/type kbio/GeneSpecificGorGPClass)

   (?/r1 owl/someValuesFrom ?/gene)
   (?/r1 owl/onProperty kso/has_indirect_template)
   (?/geneProduct rdfs/subClassOf ?/r1))

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }
