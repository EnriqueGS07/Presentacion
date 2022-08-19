var Texto = function(){
    this.contenido = ko.observable('');
    this.numLetras = ko.observable(0);
    this.numPalabras = ko.observable(0);
};

var ViewModel = function(){
    var self = this;
    this.texto = ko.observable(new Texto);
    this.funciones = new Funciones();
    this.color = ko.observable('light');
    this.setValoresTexto = function(){
        var textArea = $('textarea').val();
        self.texto().contenido(textArea);
        self.texto().numLetras(self.funciones.contarLetras(textArea))
        self.texto().numPalabras(self.funciones.contarPalabras(textArea));
        
    };
    this.showValores = function(){
        self.setValoresTexto();
        var numPalabras = $('#num-palabras');
        var numLetras = $('#num-letras');
        numPalabras.text('Numero de palabras: ' + self.texto().numPalabras());
        numLetras.text('Numero de letras: ' + self.texto().numLetras());
    }

    this.limpiar = function(){
        document.querySelector('textarea').value = '';
        self.showValores()
    };

    this.toggleCorreccion = function(){
        var textArea = document.querySelector('textarea');
        if(textArea.spellcheck == true){
            textArea.spellcheck = false;
        }else if(textArea.spellcheck == false){
            textArea.spellcheck = true;
        }
    };

    this.mostrarRepetidas = function () {
        var repetidas = self.funciones.calcularRepetidas(self.texto().contenido());
        var listaContenido = self.texto().contenido().split(" ");
        var posiciones = [];
        for(let currentWord of repetidas){
            for(var i = 0; i<listaContenido.length;i++){
                if(currentWord === listaContenido[i].toLowerCase()){
                    posiciones.push(i)
                }
            }
        }
        var agregadas = 0;
        posiciones = posiciones.sort(function(num1, num2){
            return num1-num2;
        })
        for(let i = 0; i<posiciones.length;i++){
            listaContenido.splice(posiciones[i]+agregadas+1,0,"</span>")
            listaContenido.splice(posiciones[i]+agregadas,0,"<span class=\"subrayada\">")
            agregadas+=2;
        }
        var text = document.querySelector('.info-repetidas');
        text.innerHTML = listaContenido.join(" ");
        text.style.display = "block";
        document.querySelector('textarea').style.display = "none";
        document.querySelector('#mostrar-repetidas').innerHTML = "Seguir editando";
        
        document.querySelector('#check-ortografia').style.display = "none";
    };

    this.toggleEditar = function(){
        var button =  document.querySelector('#mostrar-repetidas');
        if(button.innerHTML === "Seguir editando"){
            document.querySelector('.info-repetidas').style.display = "none";
            document.querySelector('textarea').style.display = "block";  
            button.innerHTML =  "Marcar palabras repetidas";
            document.querySelector('#check-ortografia').style.display = "block";
        }else{
            self.mostrarRepetidas();
            
        }
    }

    this.darkmode = function(){
    if(self.color() === 'light'){
        document.querySelector('body').style.backgroundColor = "#1c2f40";
        document.querySelector('h1').style.color = "#d9d1ba";
        document.querySelector('h3').style.color = "#d9d1ba";
        document.querySelector('p').style.color = "#d9d1ba";
        document.querySelector('#mostrar-repetidas').style.backgroundColor = "#d9d1ba";
        document.querySelector('#mostrar-repetidas').style.color = "#1c2f40";
        document.querySelector('#limpiar').style.backgroundColor = "#d9d1ba";
        document.querySelector('#limpiar').style.color = "#1c2f40";
        document.querySelector('.container-ch').style.color = "#d9d1ba";
        document.querySelector('#num-letras').style.color = "#d9d1ba";
        document.querySelector('select').style.backgroundColor = "#d9d1ba";
        document.querySelector('select').style.color = "#1c2f40";
        document.querySelector('textarea').style.backgroundColor = "#0d1627";
        document.querySelector('textarea').style.color = "white";
        document.querySelector('.info-repetidas').style.backgroundColor = "#0d1627";
        document.querySelector('.info-repetidas').style.color = "white";
        self.color('dark');
    }else{
        document.querySelector('body').style.backgroundColor = "#d9d1ba";
        document.querySelector('h1').style.color = "#1c2f40";
        document.querySelector('h3').style.color = "#1c2f40";
        document.querySelector('p').style.color = "#1c2f40";
        document.querySelector('#mostrar-repetidas').style.backgroundColor = "#1c2f40";
        document.querySelector('#mostrar-repetidas').style.color = "#d9d1ba";
        document.querySelector('#limpiar').style.backgroundColor = "#1c2f40";
        document.querySelector('#limpiar').style.color = "#d9d1ba";
        document.querySelector('.container-ch').style.color = "#1c2f40";
        document.querySelector('#num-letras').style.color = "#1c2f40";
        document.querySelector('select').style.backgroundColor = "#1c2f40";
        document.querySelector('select').style.color = "#d9d1ba";
        document.querySelector('textarea').style.backgroundColor = "#f2e9d8";
        document.querySelector('textarea').style.color = "black";
        document.querySelector('.info-repetidas').style.backgroundColor = "#f2e9d8";
        document.querySelector('.info-repetidas').style.color = "black";
        self.color('light');
    }
    }




};
ko.applyBindings(new ViewModel)