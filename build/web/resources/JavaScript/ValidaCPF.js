function validaCPF(form) {
    vCPF = form["cpf"].value;

    soma = 0;
    resto = 0;
    valida = true;

    for (i = 1; i <= 9; i++)
        soma += parseInt(vCPF.substring(i - 1, i)) * (11 - i);

    resto = (soma * 10) % 11;

    if ((resto === 10) || (resto === 11))
        resto = 0;

    if (resto !== parseInt(vCPF.substring(9, 10)))
        valida = false;

    soma = 0;

    for (i = 1; i <= 10; i++)
        soma += parseInt(vCPF.substring(i - 1, i)) * (12 - i);

    resto = (soma * 10) % 11;

    if ((resto === 10) || (resto === 11))
        resto = 0;

    if (resto !== parseInt(vCPF.substring(10, 11)))
        valida = false;

    if (valida === false)
        alert("Digite um CPF valido!");

}

function salvar(form) {
    validaCPF(form);

}



function consultacep(cep) {

    cep = cep.replace(/\D/g, "")

    url = "http://cep.correiocontrol.com.br/" + cep + ".js"

    s = document.createElement('script')

    s.setAttribute('charset', 'utf-8')

    s.src = url

    document.querySelector('head').appendChild(s)

}



function correiocontrolcep(valor) {

    if (valor.erro) {

        alert('Cep não encontrado');

        return;

    }
    ;

    document.getElementById('endereco').value = valor.logradouro

    document.getElementById('bairro').value = valor.bairro

    document.getElementById('cidade').value = valor.localidade

    document.getElementById('estado').value = valor.uf

}
