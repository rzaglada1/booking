<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Booking</title>
</head>
<body>

<#--<header class="p-3 text-dark top-0">-->
<#--    <div class="container">-->
<#--        <div class="d-flex flex-wrap align-items-center justify-content-between navbar navbar-expand-md">-->
<#--            <div class="text-left">-->
<#--                <a href="/"><img src="../../images/logo.png" alt="logo" style="width: 150px; height: 45px"></a>-->

<#--                <ul class="nav col-12 col-lg-auto mb-2 justify-content-start mb-md-0 float-md-end">-->
<#--                    <li class="h5"><a href="/pizza" class="nav-link px-2 text-dark">Піца</a></li>-->
<#--                    <li class="h5"><a href="/addons" class="nav-link px-2 text-dark">Доповнення</a></li>-->
<#--                    <li class="h5"><a href="/drinks" class="nav-link px-2 text-dark">Напої</a></li>-->
<#--                    <li class="h5"><a href="/desserts" class="nav-link px-2 text-dark">Десерти</a></li>-->
<#--                </ul>-->
<#--            </div>-->

<#--            <div class="text-end">-->
<#--                <a class="fa cart fs-4 mr-2" href="/cart">&#xf07a;</a>-->
<#--                <span class="badge badge-warning mr-2" id='lblCartCount'></span>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->
<#--</header>-->

<main class="text-center d-flex justify-content-center align-items-center view">
    <form method="post" action="/users" style="width: 400px">


        <h1 class="h3 mb-3 fw-normal">New user</h1>

        <div class="form-floating mb-2">
            <input type="text" maxlength="50" class="form-control" placeholder="Email"
                   name="email" >
            <label for="floatingEmail">Email</label>
        </div>

        <div class="form-floating mb-2">
            <input type="password"  maxlength="50" class="form-control" placeholder="Password"
                   name="password" >
            <label for="floatingPassword">Password</label>
        </div>

        <div class="form-floating mb-2">
            <input type="password" maxlength="50" class="form-control" placeholder="Password confirm"
                   name="passwordConfirm" >
            <label for="floatingPassword_confirm">Password confirm</label>
        </div>

        <div class="form-floating mb-2">
            <input type="text"  maxlength="50" class="form-control"
                   name="firstName" placeholder="First name" >
            <label for="floatingName">First name</label>
        </div>

        <div class="form-floating mb-2">
            <input type="text" maxlength="50"  class="form-control"
                   name="lastName" placeholder="Last name" >
            <label for="floatingSurname">Last Name</label>
        </div>

        <div class="form-floating mb-4">
            <input type="tel" maxlength="15" class="form-control"
                   name="phone" placeholder="Phone" >
            <label for="floatingPhone">Phone</label>
        </div>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Create</button>
    </form>
</main>

<#--<div>-->
<#--    <footer class="py-1 d-flex justify-content-around">-->
<#--        <a href="https://forms.gle/" target="_blank" class="text-center mt-3">Залиште відгук про нас!</a>-->
<#--        <p class="text-center text-muted mt-3">© Pizzaro 2022</p>-->
<#--        <p class="text-center text-muted mt-3">Підтримка: 0997165829</p>-->
<#--    </footer>-->
<#--</div>-->
<script src="https://cdn.jsdelivr.net/npm/@fancyapps/ui@4.0/dist/fancybox.umd.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
