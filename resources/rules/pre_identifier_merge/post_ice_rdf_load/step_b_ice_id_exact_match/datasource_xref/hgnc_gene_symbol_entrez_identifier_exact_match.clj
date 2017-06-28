;; -----------------------------------------------------------------
;; --------- HGNC Gene Symbol Entrez Identifier Exact Match --------
;; -----------------------------------------------------------------
`{:name "hgnc-gene-symbol-entrez-identifier-exact-match"
  :description "This rule asserts an exact match between a hgnc symbol and an entrez gene"
  :head ((?/hgnc_gene_symbol_identifier skos/exactMatch ?/ncbi_gene_identifier))
  :body ((?/record rdf/type ccp/IAO_EXT_0000055) ; CCP:HGNC_gene_record
          (?/record obo/BFO_0000051 ?/gene_symbol_field_value)
          (?/gene_symbol_field_value rdf/type ccp/IAO_EXT_0000042) ; CCP:HGNC_gene_record_gene_symbol_field_value
          (?/gene_symbol_field_value rdf/type ?/hgnc_gene_symbol_identifier)
          (?/hgnc_gene_symbol_identifier rdfs/subClassOf ccp/IAO_EXT_0000186) ; CCP:HGNC_gene_symbol_identifier
          (?/record obo/BFO_0000051 ?/ncbi_gene_identifier_field_value)
          (?/ncbi_gene_identifier_field_value rdf/type ccp/IAO_EXT_0000675) ; CCP:HGNC_gene_record_entrez_gene_identifier_field_value
          (?/ncbi_gene_identifier_field_value rdf/type ?/ncbi_gene_identifier)
          (?/ncbi_gene_identifier rdfs/subClassOf ccp/IAO_EXT_0000084)) ; ccp:NCBI_gene_identifier
  }