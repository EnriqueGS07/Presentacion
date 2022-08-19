class Funciones {
    contarPalabras = function(string){
        var list = string.split(' ');
        var contador = 0;
        for(var i=0;i<list.length; i++){
            if(list[i] !== ''){
                contador+=1;
            };
        };
        return contador;
    }

    contarLetras = function(string){
        var contador = 0;
        for(var i=0;i<string.length; i++){
            if(/^[!-■]$/i.test(string[i])){
                contador+=1;
            };
        };
        return contador;
    };

    calcularRepetidas = function (string) {
        string = string.trim();
        var list = string.split(' ');
        var repetidas = new Set();
        for (var i = 0; i < list.length; i++) {
                list[i] = list[i].trim().toLowerCase();
        }
        for (var i = 0; i < list.length; i++) {
            for (var j = i + 1; j < list.length; j++) {
                if (list[i] === list[j] && list[i] !== '') {
                    repetidas.add(list[i]);
                }
            }
        }
        return repetidas;
    }
}