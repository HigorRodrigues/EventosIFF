function genPdf(){
                var doc = new jsPDF();
                doc.fromHTML($('#test').get(0), 20, 20, {'width':500});
                
                doc.save("teste.pdf");
            }
