Quando rodar o arquivo .jar, NÃO INICIE a janela financeiro antes de cadastrar as outras entidades,
e lembre de FECHAR CONTA dos clientes ANTES de fazer os pagamentos no financeiro. Caso não siga essas duas
instruções, o programa apresentará uma pequena falha no calculo. Porém, se você iniciar o financeiro primeiro e
cadastrar as outras entidades depois, deve apagar os dados da tabela financeiro manualmente do Postgres
(usando: DELETE FROM financeiro) e iniciar novamente o arquivo .jar.