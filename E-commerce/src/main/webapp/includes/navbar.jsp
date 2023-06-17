<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container">
    <a class="navbar-brand" href="#">Gaming Galaxy</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item active">
          <a class="nav-link" href="index.jsp">Home </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="cart.jsp">Carrello<span class="badge badge-danger px-1">${cart_list.size()}</span></a>
        </li>

        <% if (auth != null) {
          if ("administrator".equals(((User) session.getAttribute("auth")).getUserType())) { %>
          	<li class="nav-item active">
          	<a class="nav-link" href="administrator.jsp"> Modifica </a>
          	</li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Utenti
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="ordersAdministrator.jsp">I loro Ordini</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="log-out">Esci</a>
              </div>
            </li>
        <% } else { %>

            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Utente
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="orders.jsp">I miei Ordini</a>
                <a class="dropdown-item" href="datiUtente.jsp">I miei Dati</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="log-out">Esci</a>
              </div>
            </li>
        <% }
      } else { %>
        <li class="nav-item">
          <a class="nav-link" href="register.jsp">Registrati</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="login.jsp">Accedi</a>
        </li>
      <% } %>

      </ul>
     
    </div>
  </div>
</nav>

