<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta
            name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
    />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
            crossorigin="anonymous"
    />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />

    <style>
        .view {
            width: 100%;
            min-height: calc(100vh - 90px);
            /*height: 100%;*/
        }
        .no-border-radius {
            border-radius: 0;
        }
    </style>

    <title>Form</title>
</head>
<body>
<header class="p-3 text-dark top-0">
    <div class="container">
        <div
                class="d-flex flex-wrap align-items-center justify-content-between navbar navbar-expand-md"
        >
            <div class="text-left d-flex flex-row align-items-center">
                <a href="/" class="h2 text-dark" style="margin-right: 20px"
                >My booking.com</a
                >
                <#if edit??>
                    <ul
                            class="nav col-12 col-lg-auto mb-2 justify-content-start align-items-center mb-md-0 float-md-end"
                    >
                        <li class="h5">
                            <a href="/houses" class="nav-link px-2 text-dark">Мої об'єкти</a>
                        </li>
                        <li class="h5">
                            <a href="/houses/new" class="nav-link px-2 text-dark">Новий об'єкт</a>
                        </li>
                        <li class="h5">
                            <a href="/wishes" class="nav-link px-2 text-dark">Список бажань</a>
                        </li>
                        <li class="h5">
                            <a href="/orders" class="nav-link px-2 text-dark">Історія бронювань</a>
                        </li>
                    </ul>
                </#if>
            </div>

            <div class="text-end">
                <#if edit??>
                    <a
                            href="/users/edit"
                            class="btn btn-outline-warning text-dark"
                            style="margin-right: 10px"
                    >Переглянути профіль</a
                    >

                    <a
                            href="/auth/logout"
                            class="btn btn-outline-danger"
                            style="width: 70px"
                    >Вийти</a
                    >
                    <#if admin??>
                        <a href="/users" class="btn btn-outline-warning  text-dark"
                        >Користувачі</a>
                    </#if>

                <#else>
                    <a href="/auth/login" class="btn btn-outline-dark me-2">Увійти</a>
                    <a href="/users/new" class="btn btn-outline-warning text-dark"
                    >Створити акаунт</a
                    >
                </#if>
            </div>
        </div>
    </div>
</header>

<main>
    <div class="card-body bg-dark d-flex justify-content-center  view">



<#--            <form method="post" action="/users<#if edit??>/update</#if><#if idPresent?? && edit?? && admin??>/${user.id}</#if>-->
            <form method="post" action="/users<#if edit??>/update</#if><#if idPresent?? && edit??>/${user.id}</#if>

" style="width: 400px">


                <h1 class="h3 mb-3 text-light fw-normal"><#if edit??>Редагування користувача<#else>Новий користувач</#if></h1>

                <#if edit?? && admin??>
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
                                        <option>${roleEnum}</option>
                                    </#list>
                                </#if>
                            </select>
                            <label for="formControlSelectRole">Role select</label>
                        </div>
                    </#list>
                </#if>

<#--=========================email========================================-->
                <div class="form-floating mb-2">
                    <input id="floatingEmail" type="text" maxlength="50"
                           class="form-control <#if emailError?? || errorMessageDouble??>is-invalid</#if> "  placeholder="Email"
                            <#if edit??>
                                value="${user.email}" readonly
                            <#elseif new?? && user??>
                                value="${user.email}"
                            </#if>

                           name="email">
                    <label for="floatingEmail">Email</label>

                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                    <#if errorMessageDouble??>
                        <div class="invalid-feedback">
                            ${errorMessageDouble}
                        </div>
                    </#if>

                </div>

                <#--=========================password========================================-->
                <#if edit??>
                    <div class="form-floating mb-2">
                        <input id="floatingPasswordIld" type="password" maxlength="50"
                               class="form-control <#if passwordOldError??>is-invalid</#if>"
                               placeholder="Password"
                               name="passwordOld">

                        <label for="floatingPasswordIld">For change, enter old password</label>
                        <#if passwordOldError??>
                            <div class="invalid-feedback">
                                ${passwordOldError}
                            </div>
                        </#if>
                    </div>

                    <div class="form-floating mb-2">
                        <input id="floatingPasswordNew" type="password" maxlength="50"
                               class="form-control <#if errorMessagePass?? || passwordError??>is-invalid</#if>"
                               placeholder="PasswordNew"
                               name="password"
                                <#if user?? && user.password??>
                                    value="${user.password}"
                                </#if>
                        >
                        <label for="floatingPasswordNew">New password</label>
                        <#if passwordError??>
                            <div class="invalid-feedback">
                                ${passwordError}
                            </div>
                        </#if>
                    </div>


                    <div class="form-floating mb-2">
                        <input id="floatingPasswordConfirm" type="password" maxlength="50"
                               class="form-control <#if errorMessagePass??>is-invalid</#if>"
                               placeholder="Password confirm"

                               name="passwordConfirm"
                                <#if user?? && user.passwordConfirm??>
                                    value="${user.passwordConfirm}"
                                </#if>
                        >
                        <label for="floatingPasswordConfirm">New password confirm</label>
                        <#if errorMessagePass??>
                            <div class="invalid-feedback">
                                ${errorMessagePass}
                            </div>
                        </#if>

                    </div>

                <#else>
                    <div class="form-floating mb-2">
                        <input id="floatingPassword" type="password" maxlength="50"
                        class="form-control <#if errorMessagePass?? || passwordError??>is-invalid</#if> "
                               placeholder="Password"
                               name="password"
                        <#if user??>
                            value="${user.password}"
                        </#if>
                        >
                        <label for="floatingPassword">Password</label>
                        <#if passwordError??>
                            <div class="invalid-feedback">
                                ${passwordError}
                            </div>
                        </#if>
                    </div>

                    <div class="form-floating mb-2">
                        <input id="floatingPasswordConfirm" type="password" maxlength="50"
                        class="form-control <#if errorMessagePass??>is-invalid</#if> "
                               placeholder="Password confirm"
                               name="passwordConfirm"
                        <#if user??>
                            value="${user.passwordConfirm}"
                        </#if>
                        >
                        <label for="floatingPasswordConfirm">Password confirm</label>
                        <#if errorMessagePass??>
                            <div class="invalid-feedback">
                                ${errorMessagePass}
                            </div>
                        </#if>
                        <#if passwordError??>
                            <div class="invalid-feedback">
                                ${passwordError}
                            </div>
                        </#if>

                    </div>
                </#if>

                <#--=========================firstName========================================-->
                <div class="form-floating mb-2">
                    <input id="floatingName" type="text" maxlength="50"
                           class="form-control <#if firstNameError??>is-invalid</#if> "
                           name="firstName" placeholder="First name"
                            <#if user??>
                                value="${user.firstName}"
                            </#if> >
                    <label for="floatingName">First name</label>
                    <#if firstNameError??>
                        <div class="invalid-feedback">
                            ${firstNameError}
                        </div>
                    </#if>
                </div>

                <#--=========================LastName========================================-->
                <div class="form-floating mb-2">
                    <input id="floatingLastname" type="text" maxlength="50"
                           class="form-control <#if lastNameError??>is-invalid</#if>"
                           name="lastName" placeholder="Last name"
                            <#if user??>
                                value="${user.lastName}"
                            </#if>
                    >
                    <label for="floatingLastname">Last Name</label>
                    <#if lastNameError??>
                        <div class="invalid-feedback">
                            ${lastNameError}
                        </div>
                    </#if>
                </div>

                <#--=========================phone========================================-->
                <div class="form-floating mb-2">
                    <input id="floatingPhone" type="tel" maxlength="15"
                           class="form-control <#if phoneError??>is-invalid</#if>"
                           name="phone" placeholder="Phone"
                            <#if user??>
                                value="${user.phone}"
                            </#if>
                    >
                    <label for="floatingPhone">Phone</label>
                    <#if phoneError??>
                        <div class="invalid-feedback">
                            ${phoneError}
                        </div>
                    </#if>
                </div>

                <#--=========================isActive========================================-->
                <#if admin??>
                    <div class="form-check">
                        <input id="flexCheckChecked" class="form-check-input" type="checkbox"
                               name="activeForm"

                               <#if user?? && user.active>checked</#if> >
                        <label class="form-check-label text-light" for="flexCheckChecked">
                            Active
                        </label>
                    </div>
                <#else>
                    <div class="form-floating mb-4 text-light">
                        <#if edit?? && user.active??>
                            <#if user.active>active<#else>not active</#if>
                        </#if>
                    </div>
                </#if>

                <#--=========================button submit========================================-->
                <button class="w-100 btn btn-lg btn-warning"
                        type="submit"><#if edit??>Оновити<#else>Створити</#if></button>

                <#if edit??>
                    <a href="/users/delete/${user.id}"
                       class="btn w-100  btn-lg btn-danger ">
                        Видалити
                    </a>
                </#if>

            </form>
        </div>


</main>



</body>
</html>