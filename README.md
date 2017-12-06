# GitClientApp

Este app foi contruído usando [GitHub API](https://developer.github.com/v3/).

Neste App, se inicia com o consumo da uri: https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1

Esta URI retorna uma lista paginada dos repositórios, ordenados por popularidade decrescente. 

Ao tocar em um item, o app leva a lista de Pull Requests do repositório. URI: https://api.github.com/repos/<login>/<nome-repositorio>/pulls

Ao tocar em um item, o app abre no browser a página do Pull Request em questão.




