;; ---------------------------------------------------
;; --------- owl:equivalentClass id mappings ---------
;; ---------------------------------------------------
`{:name "equivalent-class-idmappings"
  :title "create skos/exactMatch links between identifiers that denote named classes that are linked via owl:equivalentClass, e.g. CL_0000000 (cell) & GO_0005623 (cell)"
  :head ((?/id1 skos/exactMatch ?/id2))
  :sparql-string "prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?id1 ?id2 {
                    ?c1 owl:equivalentClass ?c2 .
                    ?c1 oboInOwl:hasOBONamespace ?ns1 .
                    ?c2 oboInOwl:hasOBONamespace ?ns2 .
                    ?id1 obo:IAO_0000219 ?c1 .
                    ?id2 obo:IAO_0000219 ?c2 .
                  }"
  }