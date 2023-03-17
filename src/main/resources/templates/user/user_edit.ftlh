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


<main class="text-center d-flex justify-content-center align-items-center view">
    <form method="post" action="/users/${user.id?c}" style="width: 400px">


        <h1 class="h3 mb-3 fw-normal">Edit user</h1>

        <div class="form-floating mb-2">
            <input type="text" maxlength="50" class="form-control" placeholder="Email" value="${user.email}" readonly
                   name="email" >
            <label for="floatingEmail">Email</label>
        </div>

        <div class="form-floating mb-2">
            <input type="password"  maxlength="50" class="form-control" placeholder="Password" value="********"
                   name="password" >
            <label for="floatingPassword">Password</label>
        </div>

        <div class="form-floating mb-2">
            <input type="password" maxlength="50" class="form-control" placeholder="Password confirm" value="********"
                   name="password_confirm" >
            <label for="floatingPassword_confirm">Password confirm</label>
        </div>

        <div class="form-floating mb-2">
            <input type="text"  maxlength="50" class="form-control"
                   name="firstName" placeholder="First name"  value="${user.firstName}">
            <label for="floatingName">First name</label>
        </div>

        <div class="form-floating mb-2">
            <input type="text" maxlength="50"  class="form-control"
                   name="lastName" placeholder="Last name"  value="${user.lastName}" >
            <label for="floatingSurname">Last Name</label>
        </div>

        <div class="form-floating mb-2">
            <input type="tel" maxlength="15" class="form-control"
                   name="phone" placeholder="Phone" value="${user.phone}" >
            <label for="floatingPhone">Phone</label>
        </div>

        <div class="form-floating mb-4">
            <#if user.active>active<#else>not active</#if>
        </div>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Update</button>
    </form>
</main>


<script src="https://cdn.jsdelivr.net/npm/@fancyapps/ui@4.0/dist/fancybox.umd.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
