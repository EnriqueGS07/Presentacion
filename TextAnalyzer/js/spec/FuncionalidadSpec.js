describe('Funcionalidades', function(){
    var funcionalidad, cadenaAleatoria;
    beforeEach(function(){
        funcionalidad = new Funciones();
        cadenaAleatoria = longitud => {
            const banco = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";
            let aleatoria = "";
            for (var i = 0; i < longitud; i++) {
                aleatoria += banco.charAt(Math.floor(Math.random() * banco.length));
            }
            return aleatoria;
        };
        
    });

    it('Deberia contar caracteres ascii visibles en pantalla', function(){
        this.contador = 0;
        var self = this;
        var cadena = cadenaAleatoria(Math.floor((Math.random() * (9999 - 1 + 1)) + 1));
        var numLetras = funcionalidad.contarLetras(cadena);
        var letrasEsperadas = (function(){
            return function(){
                for(var i = 0; i < cadena.length; i++){
                    if(cadena[i] !== ' '){
                        self.contador += 1;
                    }
                };
            }
        })();
        letrasEsperadas()
        expect(numLetras).toBe(self.contador);
    });

    it('Deberia contar palabras', function(){
        var cadena = cadenaAleatoria(Math.floor((Math.random() * (9999 - 1 + 1)) + 1));
        var longitud = cadena.split(' ').length;
        for(var i=0;i<cadena.split(' ').length; i++){
            if(cadena.split(' ')[i] === ''){
                longitud-=1;
            };
        };
        expect(funcionalidad.contarPalabras(cadena)).toBe(longitud);
    });

    it('Deberia obtener palabras repetidas', function(){
        var cadena = "estas son las palabras repetidas estas son las palabras repetidas , el resto despues de la coma no"
        var esperadas = new Set(["estas", "son", "las", "palabras", "repetidas"]);
        expect(funcionalidad.calcularRepetidas(cadena)).toEqual(esperadas);
        
    });
})

