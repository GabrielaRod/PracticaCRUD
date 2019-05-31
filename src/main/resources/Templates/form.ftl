<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Inicio - Practica Crud</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="css/simple-sidebar.css" rel="stylesheet">
    <link href="css/menu.css" rel="stylesheet">


</head>

<body>

<div id="wrapper">

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="#">
                    Practica CRUD
                </a>
            </li>
            <li>
                <a href="/">Inicio</a>
            </li>
            <li>
                <a href="/addStudent">Agregar Estudiante</a>
            </li>
            <li>
                <a href="/viewStudents">Ver Estudiantes</a>
            </li>
        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div style="float : left">

                <div id="tituloMenu">

                    <a href="#menu-toggle"  style="text-decoration: none" class="fas fa-bars" id="menu-toggle"></a>

                    <h1 id="titulo">Agregar Estudiante</h1>

                </div>
            </div>
            <div>


            <form method="POST" action="/add">
                <div class="form-group">
                    </br></br>
                    <label style="float: left" for="matricula"><br>Matricula</label>
                    <input type="number" class="form-control" placeholder="Introduce la Matricula" min="0"  name="matricula" required>
                </div>
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" placeholder="Introduce el Nombre"  name="nombre"  required>
                </div>
                <div class="form-group">
                    <label for="apellido">Apellido</label>
                    <input type="text" class="form-control" placeholder="Introduce el Apellido" name="apellido"  required>
                </div>
                <div class="form-group">
                    <label for="telefono">Teléfono</label>
                    <input type="text" class="form-control" placeholder="Introduce el Telefono" name="telefono" required>
                </div>


                <div style="float: right">

                    <button type="submit" class="btn btn-default">Guardar</button>

                </div>


            </form>
            </div>


        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>

</body>

</html>
