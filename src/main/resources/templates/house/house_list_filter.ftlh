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


<div class="album py-5 bg-dark">
    <div class="container">
        <div
                class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-2 row-cols-xl-3 g-3"
        >
            <#list houses as house>
                <div class="col">

                    <div class="card shadow-sm h-100">
                        <img
                                src="<#if house.image?? >/images/${house.image.getId()}</#if>"
                                alt="No photo"
                                class="bd-placeholder-img card-img-top"
                                style="background-size: cover; width: 100%; height: 320px"
                        />

                        <div class="card-body d-flex flex-column">
                            <p class="card-text fw-bold h3"><#if house.name??>${house.name} <#else>Назва</#if></p>

                            <p class="card-text text-break fs-7">Локація<br>
                                <#if house.address.country??>${house.address.country},</#if>
                                <#if house.address.city??>${house.address.city},</#if>
                                <#if house.address.street?has_content>вул.${house.address.street},</#if>
                                <#if house.address.number?has_content>буд.${house.address.number},</#if>
                                <#if house.address.apartment?has_content>кв.${house.address.apartment}.</#if>
                            </p>

                            <p class="card-text text-break fs-7">
                                Опис<br><#if house.description??>${house.description}</#if></p>


                            <form class="mt-auto productForm" action="">
                                <div class="btn-group sizes w-100">
                                    <input
                                            class="btn-check"
                                            type="radio"
                                            id="scoreWord"
                                            checked
                                            disabled
                                    />
                                    <label class="btn btn-outline-secondary" for="scoreWord">
                                        Середня оцінка
                                    </label>

                                    <input
                                            class="btn-check"
                                            type="radio"
                                            id="score"
                                            checked
                                            disabled
                                    />
                                    <label class="btn btn-outline-secondary" for="score">
                                        8.9
                                    </label>
                                </div>
                                <div class="d-flex flex-column ">
                                    <a href="/feedbacks/${house.id}"
                                    >
                                        123 відгуки
                                    </a>
                                </div>

                                <span class="d-flex align-items-center justify-content-between mt-2">

                    <span
                            class="fw-bold fs-4 priceDisc product text-warning"
                            style="flex-grow: 2"
                    >
                        Вартість
                    </span>
                    <span
                            class="fw-bold fs-4 price product"
                            style="flex-grow: 2"
                    >
                        X грн
                    </span>

                  </span>
                            </form>


                        </div>

                        <div class="d-flex flex-column btn-group text-center">
                            <a href="/houses/${house.id}/booking"
                                    class="btn fw-bold btn-sm btn-warning text-dark mb-1 mx-5 py-2 rounded "
                            >
                                Забронювати
                            </a>
                            <a href="/houses/${house.id}/detail"
                                    class="btn fw-bold btn-sm btn-success mb-3 mx-5 py-2 rounded"
                            >
                                Деталі
                            </a
                            >
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>

</div>


</body>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"
></script>

</body>
</html>