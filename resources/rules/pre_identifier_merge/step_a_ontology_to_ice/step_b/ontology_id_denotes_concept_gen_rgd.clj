;; ------------------------------------------------------------------------
;; --------- Ontology Concept Identifier Denotes Ontology Concept ---------
;; ------------------------------------------------------------------------
;; create id denotes concept triple for every ontology concept; this rule handles the rgd URIs used by pr.owl
`{:name "ontology-id-denotes-concept-gen-rgd"
  :description "This rule generates an ontology concept identifier for every non-root ontology concept with the RGD namespace."
  :head ((?/id obo/IAO_0000219 ?/ontology_concept) ; denotes
         (?/id rdf/type ccp/IAO_EXT_0000088) ; ontology concept identifier
         (?/id rdf/type ccp/IAO_EXT_0000331)) ; ccp:RGD gene identifier
  :reify ([?/id {:ln (:regex ":" "_" ?/concept_id)
                 :ns "ccp" :prefix "" :suffix ""}])
  :sparql-string "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?ontology_concept ?concept_id {
                  ?ontology_concept oboInOwl:id ?concept_id .
                  # include only concepts with the RGD namespace
                  filter (contains (str(?ontology_concept), 'http://rgd.mcw.edu/rgdweb/report/gene/main.html'))
                  minus {?ontology_concept owl:deprecated true} .
                  minus {?ontology_concept rdf:type ccp:IAO_EXT_0000190} . #ccp:ontology root concept identifier
                  ?ontology_concept rdfs:subClassOf* ?root_class .
                  ?root_id obo:IAO_0000219 ?root_class . #obo:denotes
                  ?root_id rdf:type ccp:IAO_EXT_0000190 . #ccp:ontology root concept identifier
  }"
  }