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

    <form method="post" action="/users<#if user?? && !errorMessage??>/update</#if>

<#if user?? && !errorMessage?? && admin??>/${user.id}</#if>


" style="width: 400px">


        <h1 class="h3 mb-3 fw-normal"><#if user?? && !errorMessage??>Edit user<#else>Create user</#if></h1>

        <#if user?? && admin??>
        <#list user.roles as role>
        <div class="form-floating mb-2">
            <select class="form-control" id="formControlSelectRole" name="roleForm">
                <#if user.roles??>
                    <#list user.roles as rol>
                        <option>${rol}</option>
                    </#list>
                </#if>

                <#if rolesEnum??>
                <#list rolesEnum as roleEnum>
                <option >${roleEnum}</option>
                </#list>
                </#if>
            </select>
            <label for="formControlSelectRole">Role select</label>
        </div>
        </#list>
        </#if>


        <div class="form-floating mb-2">
            <input id ="floatingEmail" type="text" maxlength="50" class="form-control" placeholder="Email"
                   <#if user?? && !errorMessage??>
                   value="${user.email}" readonly
                   </#if>
                    <#if user?? && errorMessagePass??>
                   value = "${user.email}"
                   </#if>
                   name="email" >
            <label for="floatingEmail">Email</label>
        </div>

        <#if user?? && !errorMessage??>
            <div class="form-floating mb-2">
                <input id ="floatingPasswordIld" type="password"  maxlength="50" class="form-control" placeholder="Password"
                       name="passwordOld">

                <label for="floatingPasswordIld">For change, enter old password</label>
            </div>

            <div class="form-floating mb-2">
                <input id ="floatingPasswordNew" type="password"  maxlength="50" class="form-control" placeholder="PasswordNew"
                       name="password" >
                <label for="floatingPasswordNew">New password</label>
            </div>


            <div class="form-floating mb-2">
                <input id ="floatingPasswordConfirm" type="password" maxlength="50" class="form-control" placeholder="Password confirm"

                       name="passwordConfirm" >
                <label for="floatingPasswordConfirm">New password confirm</label>
            </div>

            <#else>
                <div class="form-floating mb-2">
                    <input id ="floatingPassword" type="password"  maxlength="50" class="form-control" placeholder="Password"
                           name="password" >
                    <label for="floatingPassword">Password</label>
                </div>

                <div class="form-floating mb-2">
                    <input id="floatingPasswordConfirm" type="password" maxlength="50" class="form-control" placeholder="Password confirm"
                           name="passwordConfirm" >
                    <label for="floatingPasswordConfirm">Password confirm</label>
                </div>
                <#if  errorMessagePass??>
                    <p class="text-danger">${errorMessagePass}</p>
                </#if>
        </#if>



        <div class="form-floating mb-2">
            <input id ="floatingName" type="text"  maxlength="50" class="form-control"
                   name="firstName" placeholder="First name"
                   <#if user??>
                   value="${user.firstName}"
                   </#if>            >

            <label for="floatingName">First name</label>
        </div>

        <div class="form-floating mb-2">
            <input id = "floatingLastname" type="text" maxlength="50"  class="form-control"
                   name="lastName" placeholder="Last name"
                   <#if user??>
                   value="${user.lastName}"
                   </#if>
            >
            <label for="floatingLastname">Last Name</label>
        </div>

        <div class="form-floating mb-2">
            <input id = "floatingPhone" type="tel" maxlength="15" class="form-control"
                   name="phone" placeholder="Phone"
                   <#if user??>
                   value="${user.phone}"
                   </#if>
            >
            <label for="floatingPhone">Phone</label>
        </div>

        <#if admin??>
            <div class="form-check">
                <input id="flexCheckChecked" class="form-check-input" type="checkbox"
                       name="activeForm"

                       <#if user.active>checked</#if> >
                <label class="form-check-label" for="flexCheckChecked">
                    Active
                </label>
            </div>
        <#else>
        <div class="form-floating mb-4">
            <#if user?? && !errorMessage??>
            <#if user.active>active<#else>not active</#if>
            </#if>
        </div>
        </#if>

        <div class="form-floating mb-4">
            <#if user??>
                <#if errorMessageDouble??>
                    <p class="text-danger">${errorMessageDouble}</p>
                </#if>
            </#if>
        </div>


        <button class="w-100 btn btn-lg btn-warning" type="submit"><#if user?? && !errorMessage??>Оновити<#else>Створити</#if></button>

        <#if user?? && !errorMessage??>
        <a href="/users/delete<#if admin??>/${user.id}</#if>"
           class="btn w-100  btn-lg btn-dark ">
            Видалити
        </a>
        </#if>

    </form>

</main>
</body>

</html>
