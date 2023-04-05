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

    <form method="post" action="/users<#if user??>/${user.id?c}</#if>" style="width: 400px">


        <h1 class="h3 mb-3 fw-normal"><#if user??>Edit user<#else>Create user</#if></h1>

        <div class="form-floating mb-2">
            <input type="text" maxlength="50" class="form-control" placeholder="Email"
                   <#if user??>
                   value="${user.email}" readonly
                   </#if>
                   name="email" >
            <label for="floatingEmail">Email</label>
        </div>

        <#if user??>
            <div class="form-floating mb-2">
                <input type="password"  maxlength="50" class="form-control" placeholder="Password"
                       name="passwordOld" >
                <label for="floatingPassword">For change, enter old password</label>
            </div>

            <div class="form-floating mb-2">
                <input type="password"  maxlength="50" class="form-control" placeholder="Password"
                       name="password" >
                <label for="floatingPassword">New password</label>
            </div>


            <div class="form-floating mb-2">
                <input type="password" maxlength="50" class="form-control" placeholder="Password confirm"

                       name="password_confirm" >
                <label for="floatingPassword_confirm">New password confirm</label>
            </div>

            <#else>
                <div class="form-floating mb-2">
                    <input type="password"  maxlength="50" class="form-control" placeholder="Password"
                           name="password" >
                    <label for="floatingPassword">Password</label>
                </div>

                <div class="form-floating mb-2">
                    <input type="password" maxlength="50" class="form-control" placeholder="Password confirm"
                           name="password_confirm" >
                    <label for="floatingPassword_confirm">Password confirm</label>
                </div>
        </#if>



        <div class="form-floating mb-2">
            <input type="text"  maxlength="50" class="form-control"
                   name="firstName" placeholder="First name"
                   <#if user??>
                   value="${user.firstName}"
                   </#if>
            >

            <label for="floatingName">First name</label>
        </div>

        <div class="form-floating mb-2">
            <input type="text" maxlength="50"  class="form-control"
                   name="lastName" placeholder="Last name"
                   <#if user??>
                   value="${user.lastName}"
                   </#if>
            >
            <label for="floatingSurname">Last Name</label>
        </div>

        <div class="form-floating mb-2">
            <input type="tel" maxlength="15" class="form-control"
                   name="phone" placeholder="Phone"
                   <#if user??>
                   value="${user.phone}"
                   </#if>
            >
            <label for="floatingPhone">Phone</label>
        </div>

        <div class="form-floating mb-4">
            <#if user??>
            <#if user.active>active<#else>not active</#if>
            </#if>
        </div>

        <button class="w-100 btn btn-lg btn-warning" type="submit"><#if user??>Оновити<#else>Створити</#if></button>

        <#if user??>
        <a href="/users/${user.id}/delete"
           class="btn w-100  btn-lg btn-dark ">
            Видалити
        </a>
        </#if>

    </form>

</main>
</body>

<script src="https://cdn.jsdelivr.net/npm/@fancyapps/ui@4.0/dist/fancybox.umd.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>


</html>
