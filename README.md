# Atividade 014 - Padrão Proxy

Refatoração do sistema de galeria de imagens utilizando o padrão **Proxy** na modalidade **Proxy Virtual**, incluindo o desafio adicional do proxy de proteção.

## Estrutura

| Classe | Papel |
|---|---|
| `Image` | Sujeito: abstração com `display()` |
| `HighResolutionImage` | Sujeito real: carrega a imagem no construtor e exibe em `display()` |
| `ImageProxy` | Proxy virtual: guarda o nome do arquivo e só cria a imagem real na primeira exibição |
| `ProtectedImageProxy` | Proxy de proteção (desafio adicional): só repassa a exibição se houver permissão |
| `Main` | Monta a galeria com proxies e demonstra o carregamento sob demanda e o controle de acesso |

## Como executar

```bash
javac -d out src/*.java
java -cp out Main
```

## Questões para reflexão

**a) Qual problema existente no código inicial foi resolvido com o Proxy Virtual?**

No código inicial, todas as imagens eram carregadas ao criar a galeria, mesmo que só uma fosse exibida. Com muitas imagens, a galeria demoraria para abrir e gastaria memória com imagens que talvez nunca fossem vistas. Com o proxy, cada imagem só é carregada quando for exibida pela primeira vez.

**b) Quais classes representam o sujeito, o sujeito real e o proxy na sua implementação?**

- Sujeito: `Image`
- Sujeito real: `HighResolutionImage`
- Proxy: `ImageProxy` (e, no desafio adicional, `ProtectedImageProxy`)

**c) Por que o proxy e o objeto real devem implementar a mesma abstração?**

Para que o cliente possa usar um no lugar do outro sem perceber a diferença. A galeria é uma lista de `Image` e só chama `display()`. Ela não precisa saber se está falando com um proxy ou com a imagem real, nem verificar o tipo concreto.

**d) Como o carregamento sob demanda altera o comportamento da galeria? Qual custo ainda existe na primeira exibição de uma imagem?**

A galeria passa a abrir rapidamente, porque criar um proxy é barato: ele só guarda o nome do arquivo. As imagens que nunca são exibidas nunca são carregadas, como aconteceu com a terceira na demonstração. O custo do carregamento não some, só é adiado. Ele aparece na primeira vez que a imagem é exibida, e essa primeira exibição fica mais lenta que as seguintes.

**e) Embora Proxy e Decorator possam envolver outro objeto com a mesma interface, como suas intenções se diferenciam?**

O Decorator serve para acrescentar comportamento ao objeto, e normalmente vários decoradores são empilhados para somar funcionalidades. O Proxy serve para controlar o acesso ao objeto: decidir quando ele é criado (proxy virtual), se pode ser usado (proxy de proteção) ou como é acessado (proxy remoto, cache). O proxy não muda o que o objeto faz, apenas controla o caminho até ele.
