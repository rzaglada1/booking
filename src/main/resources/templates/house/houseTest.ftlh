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
    <title>List house</title>
</head>
<body>
<main
        class="form-signin text-center d-flex justify-content-center align-items-center flex-column view"
>

    <h1 class="h3 mb-3 fw-normal">Мої об'єкти</h1>

    <#list houses as house>
        <div class="border rounded mb-2 p-3 d-flex flex-column">
            <#if house.name??>
                <h1 class="h4 mb-3 fw-normal">${house.name}</h1>
            </#if>

            <div class="d-flex align-items-center justify-content-around gap-3">
                <div class="col">
                    <#if house.address.country??>
                    <h6 class="h5 mb-3 fw-normal">${house.address.country},</h6>
                    </#if>
                </div>
                <div class="col">
                    <div class="form-floating mb-2">
                        <#if house.address.city??>
                        <h1 class="h5 mb-3 fw-normal">${house.address.city},</h1>
                        </#if>
                    </div>
                </div>
                <div class="col">
                    <div class="form-floating mb-2">
                        <#if house.address.street?has_content>
                        <h1 class="h5 mb-3 fw-normal">вул.${house.address.street},</h1>
                        </#if>
                    </div>
                </div>
                <div class="col">
                    <div class="form-floating mb-2">
                        <#if house.address.number?has_content>
                        <h1 class="h5 mb-3 fw-normal">буд.${house.address.number}.</h1>
                        </#if>
                    </div>
                </div>
                <div class="col">
                    <div class="form-floating mb-2">
                        <#if house.address.apartment?has_content>
                        <h1 class="h5 mb-3 fw-normal">кв.${house.address.apartment}.</h1>
                        </#if>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col">

                    <img src="<#if house.image?? >/images/${house.image.getId()}</#if>"
                         alt="No photo"
                         class="img-thumbnail"
                         class="img-fluid"
                         width="150"
                         height="100"
                    >

                </div>
                <div class="col">
                    <div class="form-floating mb-2">
                        <p class="h6 mb-3 fw-normal text-start">${house.description}.</p>
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-between">
                <a href="/houses/${house.id}/edit" class="btn-sm btn-info "
                   style="margin-right: auto">Редагувати...</a>


                <a href="/houses/${house.id}/delete" class="btn-sm btn-danger "
                   style="margin-left: auto">Видалити...</a>
            </div>
        </div>

    </#list>


</main>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"
></script>

</body>
</html>
