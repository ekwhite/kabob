`{
  :name "add-reactome-protein-locations-to-bio-step-b"
  :description "This rule finds any protein record described in Reactome and traces out its link to a GO CC location."
  ;; (run-rules "http://amc-tantor.ucdenver.pvt:10035" "kabob-dev" "kb2" "kb2" "/Users/elizabethwhite/Documents/kabob-rules/output/human/revised/tier0/" "/Users/elizabethwhite/Documents/kabob-rules/reactome/human/revised/tier1/create-protein-bioentity-reactome-1a.clj")
  
  :head (
         (?/localization_of_restriction rdf/type owl/Restriction)
         (?/localization_of_restriction owl/onProperty obo/RO_0002313) ;transports or maintains localization of
         (?/localization_of_restriction owl/someValuesFrom ?/original_foo)
         (?/prot_compartment rdfs/subClassOf ?/gocc_bio)
         (?/localization_to_restriction rdf/type owl/Restriction)
         (?/localization_to_restriction owl/onProperty obo/RO_0002339) ; has target end location
         (?/localization_to_restriction owl/someValuesFrom ?/prot_compartment)
         (?/localization_restriction rdfs/subClassOf obo/GO_0051179) ;localization
         (?/localization_restriction rdfs/subClassOf ?/localization_of_restriction)
         (?/localization_restriction rdfs/subClassOf ?/localization_to_restriction)
         


)
  
  :reify ([?/localization_to_restriction {:ln (:restriction)
                                          :ns "ccp" :prefix "RS_"}]
          [?/localization_of_restriction {:ln (:restriction)
                                          :ns "ccp" :prefix "RS_"}]
          [?/localization_restriction {:ln (:sha-1 "GO_0051179" ?/localization_from_restriction ?/localization_to_restriction)
                                       :ns "ccp" :prefix "LOC_"}]
          ;; double check this
          [?/prot_compartment {:ln (:sha-1 ?/gocc_id ?/original_foo)
                               :ns "ccp" }])

  :body (
         
         (:graph <http://kabob.ucdenver.edu/ekw/reactome-ice>
                 
                 (?/prot_record rdf/type ccp/IAO_EXT_EKW_0010007)  ;; Reactome protein record
                 (?/prot_record obo/IAO_0000219 ?/original_foo) ;; denotes
                 
                 
                 (?/prot_record obo/BFO_0000051 ?/location_record)
                 (?/location_record rdf/type ccp/IAO_EXT_EKW_0010116) ;; Reactome cellular location vocabulary record
                 (?/location_record obo/BFO_0000051 ?/gocc_xref_record)  ;; has_part
                 (?/gocc_xref_record rdf/type ccp/IAO_EXT_EKW_0010090) ;; unification xref record 
         
                 (?/gocc_xref_record obo/BFO_0000051 ?/gocc_xref_id_field)
         
                 (?/gocc_xref_id_field rdf/type ccp/IAO_EXT_EKW_0010123) ;; Gene Ontology field value
                 (?/gocc_xref_id_field rdfs/label ?/gocc_id) ;; Gene Ontology field value
                 
                 )
         ;; not sure about this
         ;;(:bind (:as (:concat (:str "http://ccp.ucdenver.edu/obo/ext#/UNIPROT_") (:str ?/uniprot_id)) ?/uniprot_ice))
         ;;(:bind (:as (:iri (:str ?/uniprot_ice)) ?/uniprot_uri))
         ;;(?/uniprot_ice obo/IAO_0000219 ?/uniprot_bio)

         (:bind (:as (:concat (:str "http://ccp.ucdenver.edu/obo/ext#/GENE_ONTOLOGY_") (:str ?/gocc_id)) ?/gocc_ice))
         (:bind (:as (:iri (:str ?/gocc_ice)) ?/gocc_uri))
         (?/gocc_identifier rdf/type ?/gocc_uri)
         (?/gocc_identifier obo/IAO_0000219 ?/gocc_bio)
         
         
         ;;(?/gocc_ice obo/IAO_0000219 ?/gocc_bio)

         
         )
  :sparql-string "PREFIX franzOption_memoryLimit: <franz:85g> 
PREFIX franzOption_memoryExhaustionWarningPercentage: <franz:95> 
PREFIX franzOption_logQuery: <franz:yes> 
PREFIX franzOption_clauseReorderer: <franz:identity> 
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext#> 
PREFIX obo: <http://purl.obolibrary.org/obo/> 
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> 
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> 
SELECT ?original_foo ?gocc_bio ?gocc_id 
WHERE {  GRAPH <http://kabob.ucdenver.edu/ekw/reactome-ice> { 
 ?prot_record <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext#IAO_EXT_EKW_0010007> .  
 ?prot_record <http://purl.obolibrary.org/obo/IAO_0000219> ?original_foo .  
 ?prot_record <http://purl.obolibrary.org/obo/BFO_0000051> ?location_record .  
 ?location_record <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext#IAO_EXT_EKW_0010116> .  
 ?location_record <http://purl.obolibrary.org/obo/BFO_0000051> ?gocc_xref_record .  
 ?gocc_xref_record <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext#IAO_EXT_EKW_0010090> .  
 ?gocc_xref_record <http://purl.obolibrary.org/obo/BFO_0000051> ?gocc_xref_id_field .  
 ?gocc_xref_id_field <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext#IAO_EXT_EKW_0010123> .  
 ?gocc_xref_id_field <http://www.w3.org/2000/01/rdf-schema#label> ?gocc_id .  
 }
  
 bind (  concat( str(\"http://ccp.ucdenver.edu/obo/ext#/GENE_ONTOLOGY_\"@en) ,  str(?gocc_id) )  AS ?gocc_ice )  
 bind ( iri ( str ( str(?gocc_ice) ) ) AS ?gocc_uri )  
 ?gocc_identifier <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?gocc_uri .  
 ?gocc_identifier <http://purl.obolibrary.org/obo/IAO_0000219> ?gocc_bio .  
} "
  
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"]
                             ["franzOption_clauseReorderer" "franz:identity"]]}

          
  }
