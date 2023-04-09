<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta
            name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
    />
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
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
    <title>Wish list house</title>
</head>
<body>


<div class="album py-5 bg-dark">
    <div class="container">
        <div
                class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-2 row-cols-xl-3 g-3"
        >
            <#list users as user>
                <div class="col">
                    <div class="card shadow-sm h-100">

                        <div class="card-body d-flex flex-column">

                            <form method="get" action="/users/edit/${user.id}" style="width: 400px">

                                <#if user??>
                                    <#list user.roles as role>
                                <div class="form-floating mb-2">
                                    <input type="text" maxlength="50" class="form-control" placeholder="id"
                                            value="${role}"
                                           name="role"
                                           readonly>
                                    <label for="floatingEmail">role</label>
                                </div>
                                    </#list>
                                </#if>

                                <div class="form-floating mb-2">
                                    <input type="text" maxlength="50" class="form-control" placeholder="id"
                                            <#if user??>
                                                value="${user.id}"
                                            </#if>
                                           name="id"
                                           readonly>
                                    <label for="floatingEmail">id</label>
                                </div>

                                <div class="form-floating mb-2">
                                    <input type="text" maxlength="50" class="form-control" placeholder="Email"
                                            <#if user??>
                                                value="${user.email}"
                                            </#if>
                                           name="email"
                                           readonly>
                                    <label for="floatingEmail">Email</label>
                                </div>



                                <div class="form-floating mb-2">
                                    <input type="text"  maxlength="50" class="form-control"
                                           name="firstName" placeholder="First name"
                                            <#if user??>
                                                value="${user.firstName}"
                                            </#if>
                                           readonly>

                                    <label for="floatingName">First name</label>
                                </div>

                                <div class="form-floating mb-2">
                                    <input type="text" maxlength="50"  class="form-control"
                                           name="lastName" placeholder="Last name"
                                            <#if user??>
                                                value="${user.lastName}"
                                            </#if>
                                           readonly>
                                    <label for="floatingSurname">Last Name</label>
                                </div>

                                <div class="form-floating mb-2">
                                    <input type="tel" maxlength="15" class="form-control"
                                           name="phone" placeholder="Phone"
                                            <#if user??>
                                                value="${user.phone}"
                                            </#if>
                                           readonly>
                                    <label for="floatingPhone">Phone</label>
                                </div>

                                <div class="form-floating mb-4">
                                    <#if user??>
                                        <#if user.active>active<#else>not active</#if>
                                    </#if>
                                </div>

                                <button class="w-100 btn btn-lg btn-warning" type="submit">Деталі</button>

                            </form>

                        </div>

                    </div>
                </div>
            </#list>
        </div>
    </div>

</div>

</body>
</html>