<#macro headStyle>
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
                <#if user?? && !errorMessage??>
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
                <#if user?? && !errorMessage??>
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

<#nested >


<#--    <footer class="py-3 d-flex navbar ">-->
<#--        <p class="text-center text-muted mt-3">Додаткова інформація</p>-->
<#--        <p class="text-center text-muted mt-3">© Copyright 2023</p>-->
<#--        <p class="text-center text-muted mt-3">Додаткова інформація</p>-->
<#--    </footer>-->


</body>
</html>

</#macro>