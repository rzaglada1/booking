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
            height: calc(100vh - 160px);
        }
        .no-border-radius {
            border-radius: 0;
        }
        .b
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
                <#if user.email??>
                <ul
                        class="nav col-12 col-lg-auto mb-2 justify-content-start align-items-center mb-md-0 float-md-end"
                >
                    <li class="h5">
                        <a href="/houses" class="nav-link px-2 text-dark">Мої об'єкти</a>
                    </li>
                    <li class="h5">
                        <a href="/houses/new" class="nav-link px-2 text-dark">Додати новий об'єкт</a>
                    </li>
                </ul>
                </#if>
            </div>

            <div class="text-end">
                <#if user.email??>
                <a
                        href="/users/${user.id}/edit"
                        class="btn btn-outline-warning text-dark"
                        style="margin-right: 10px"
                >Переглянути профіль</a
                >

                <a
                        href="/logout"
                        class="btn btn-outline-danger"
                        style="width: 70px"
                >Вийти</a
                >
                <#else>
                <a href="/login" class="btn btn-outline-dark me-2">Увійти</a>
                <a href="/users/new" class="btn btn-outline-warning text-dark"
                >Створити акаунт</a
                >
                </#if>
            </div>
        </div>
    </div>
</header>
<main
        class="px-3 text-center text-white bg-dark d-flex align-items-center justify-content-center flex-column view"
>
    <p class="lead text-start fs-1" style="width: 50vw">
        Відшукайте свою наступну зупинку
    </p>
    <form method="get" action="/find" class="d-flex">
        <div class="form-floating" style="width: 350px">
            <input
                    type="text"
                    class="form-control"
                    id="destination"
                    name="destination"
                    placeholder="Куди ви хочете поїхати?"
                    style="border-radius: 0.25rem 0 0 0.25rem;"
            />
            <label class="text-dark" for="destination"
            >Куди ви хочете поїхати?</label
            >
        </div>
        <div class="form-floating" style="width: 270px">
            <input
                    type="date"
                    class="form-control no-border-radius"
                    id="date"
                    name="date"
                    placeholder="На яку дату плануєте подорож?"
            />
            <label class="text-dark" for="date"
            >На яку дату плануєте подорож?</label
            >
        </div>
        <div class="form-floating">
            <input
                    type="number"
                    min="1"
                    step="1"
                    class="form-control no-border-radius"
                    id="days"
                    name="days"
                    placeholder="На скільки днів?"
            />
            <label class="text-dark" for="days">На скільки днів?</label>
        </div>
        <div class="form-floating">
            <input
                    type="number"
                    min="1"
                    step="1"
                    class="form-control no-border-radius"
                    id="people"
                    name="people"
                    placeholder="На скільки людей?"
            />
            <label class="text-dark" for="people">На скільки людей?</label>
        </div>
        <div>
            <button class="h-100 fs-4 btn btn-primary" type="submit"
                    style="border-radius: 0 0.25rem 0.25rem 0;">Пошук</button>
        </div>
    </form>
</main>
<div>
    <footer class="py-1 d-flex justify-content-around">
        <p class="text-center text-muted mt-3">Додаткова інформація</p>
        <p class="text-center text-muted mt-3">© Copyright 2023</p>
        <p class="text-center text-muted mt-3">Додаткова інформація</p>
    </footer>
</div>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"
></script>
</body>
</html>
