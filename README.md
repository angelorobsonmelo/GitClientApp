# GitClientApp

Este App foi contruído usando [GitHub API](https://developer.github.com/v3/).

Este App, se inicia com o consumo da URI: https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1
Esta URI retorna uma lista paginada dos repositórios, ordenados por popularidade decrescente. 
Ao tocar em um item, o app leva a lista de Pull Requests do repositório. URI: https://api.github.com/repos/"nome do usuário"/"nome do repositório"/pulls
Ao tocar em um item, o app abre no browser a página do Pull Request em questão.




