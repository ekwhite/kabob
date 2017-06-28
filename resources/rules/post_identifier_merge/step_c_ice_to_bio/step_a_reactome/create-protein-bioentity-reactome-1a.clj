`{
  :name "add-reactome-proteins-to-bio-step-a"
  :description "This rule finds any protein record described in Reactome and traces out its link to a unification xref to extract the protein's Reactome ID, and its link to a protein reference to extract its UniProt ID.  It creates a new subclass of the UniProt protein on the BIO side, as well as an ICE node for the Reactome ID in the protein's record, and a denotes link from the protein record to the new protein.  Several Reactome protein records may correspond to the same UniProt ID, but the different records represent the protein in different modified forms or in different cellular compartments."
  ;; (run-rules "http://amc-tantor.ucdenver.pvt:10035" "kabob-dev" "kb2" "kb2" "/Users/elizabethwhite/Documents/kabob-rules/output/human/revised/tier0/" "/Users/elizabethwhite/Documents/kabob-rules/reactome/human/revised/tier1/create-protein-bioentity-reactome-1a.clj")
  
  :head (
         ;;(?/reactome_prot_bio rdfs/subClassOf ?/uniprot_bio)
         (?/protein_record obo/IAO_0000219 ?/reactome_prot_bio)
         (?/reactome_id_field rdf/type ?/react_uri) ;; check with Bill
         (?/react_uri rdfs/subClassOf ccp/IAO_EXT_EKW_0010130) ;; Reactome identifier
         ;; http://ccp.ucdenver.edu/REACTOME_12345 rdfs:subClassOf ccp:IAO_EXT_xxxxxxxx
         ;; Where ccp:IAO_EXT_xxxxxxxx is ‘Reactome identifier’

)
  
  :reify ([?/reactome_prot_bio {:ln (:sha-1 "Reactome protein" ?/react_uri)
                                :ns "ccp" :prefix "B_"}] ;; check with Bill
          [?/react_uri {:ln (:sha-1 "Reactome ICE identifier" ?/react_id)
                                :ns "ccp" :prefix ""}] ;; check with Bill
          
          )
  :body (
         
         (:graph <http://kabob.ucdenver.edu/ekw/reactome-ice>
                 (?/prot_record rdf/type ccp/IAO_EXT_EKW_0010007)  ;; Reactome protein record
                 (?/prot_record obo/BFO_0000051 ?/xref_record)
                 (?/xref_record rdf/type ccp/IAO_EXT_EKW_0010090) ;; unification xref record
                 (?/xref_record obo/BFO_0000051 ?/reactome_id_field) ;; Reactome identifier
                 (?/reactome_id_field rdf/type ccp/IAO_EXT_EKW_0010011)  ;; Reactome identifier field
                 (?/reactome_id_field rdfs/label ?/react_id)  ;; Reactome identifier field
                 (?/prot_record obo/BFO_0000051 ?/entity_record)  ;; has_part
                 (?/entity_record rdf/type ccp/IAO_EXT_EKW_0010064) ;; Reactome protein reference record
                 (?/entity_record obo/BFO_0000051 ?/entity_xref_record)  ;; has_part
                 (?/entity_xref_record rdf/type ccp/IAO_EXT_EKW_0010090) ;; unification xref record 
         
                 (?/entity_xref_record obo/BFO_0000051 ?/entity_xref_id_field)
         
                 (?/entity_xref_id_field rdf/type ccp/IAO_EXT_EKW_0010018) ;; uniprot field value
                 (?/entity_xref_id_field rdfs/label ?/uniprot_id) ;; uniprot field value
                 
                 )
         
         (:bind (:as (:concat (:str "http://ccp.ucdenver.edu/obo/ext/REACTOME_") (:str ?/react_id)) ?/react_ice))
         (:bind (:as (:iri (:str ?/react_ice)) ?/react_uri))
         
         
         )
  
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"]
                             ["franzOption_clauseReorderer" "franz:identity"]]}

          
  }
