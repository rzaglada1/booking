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
    <style>
        a {
            text-decoration: none;
            color: #000;
        }

        .cardHr {
            border: none;
            width: 95%;
            margin: auto;
            height: 2px !important;
            background-color: #fff;
        }

        .view {
            width: 100%;
            min-height: calc(100vh - 160px);
        }

        .abc {
            padding-left: 40px;
        }

        .para {
            float: right;
            margin-right: 0;
            padding-left: 80%;
            top: 0;
        }

        li {
            list-style: none;
            line-height: 50px;
        }

        .col-md-2 a {
            text-decoration: none;
        }
    </style>
    <title>Form</title>
</head>
<body>

<main
        class="px-3 text-center text-white bg-dark d-flex align-items-center justify-content-center flex-column view"
>
    <div class="container py-4 my-4 mx-auto d-flex flex-column">
        <div class="header">
            <div class="row justify-content-around">
                <div class="col-md-9 abc">
                    <h1>
                        <#if house??>
                            ${house.name}
                        <#else>
                            Назва
                        </#if>
                    </h1>
                </div>
                <div
                        class="btn btn-secondary d-flex justify-content-center align-items-center fs-3 col-md-1 text-right"
                >
                    <#if house.getAverRating() == -1>без оцінок<#else>${house.getAverRating()}</#if>
                </div>
                <p class="text-right para">
                    <#if house.getCountFeedback() != 0>На основі ${house.getCountFeedback()} відгуків</#if></p>
            </div>
        </div>
        <div class="container-body mt-4">
            <div class="row r3">
                <div class="col-md-5">
                    <#if house??>
                        <#if house.image??>
                            <img src="/images/${house.image.getId()}" alt="No photo" width="90%" height="95%">
                        <#else>
                            <img src="" alt="No photo">
                        </#if>
                    </#if>
                </div>
                <div class="col-md-7 p-1 border rounded position-relative">
                    <ul style="padding-left: 0">
                        <li>Опис житла: <br><#if house??>${house.description}</#if>
                            <hr align="center" class="cardHr">
                        </li>
                        <li>Адреса: <br>
                            <#if house.address.country??>${house.address.country},</#if>
                            <#if house.address.city??>${house.address.city},</#if>
                            <#if house.address.street?has_content>вул.${house.address.street},</#if>
                            <#if house.address.number?has_content>буд.${house.address.number},</#if>
                            <#if house.address.apartment?has_content>кв.${house.address.apartment}.</#if>
                            <hr class="cardHr">
                        </li>
                    </ul>

                    <div class="d-flex justify-content-center align-items-stretch mb-3" style="gap: 10px">
                        <div class="form-floating">
                            <input
                                    type="date"
                                    class="form-control"
                                    id="date1"
                                    name="date1"
                            />
                            <label class="text-dark" for="date1"
                            >Дата заїзду</label
                            >
                        </div>





                        <div class="form-floating">
                            <input
                                    type="date"
                                    class="form-control"
                                    id="date2"
                                    name="date2"
                            />
                            <label class="text-dark" for="date2"
                            >Дата виїзду</label
                            >
                        </div>
                        <div class="form-floating">
                            <input
                                    type="number"
                                    min="1"
                                    class="form-control"
                                    id="people"
                                    name="people"
                            />
                            <label class="text-dark" for="people"
                            >Кількість людей</label
                            >
                        </div>
                        <a class="btn btn-warning t-center" href="/houses/${house.id}/booking"
                           style="line-height: 43px; height: 58px"
                        >Бронювати</a>
                        <form style="float: right" action="/wishes/${house.id}/new" method="post">
                            <input type="submit" class="btn btn-info h-100

                                   <#if user.email??>
                                   <#if isWishList == true>
                                   disabled "
                                   value="Вже в обраному"
                            <#else>
                                "
                                value="Додати в обране"
                            </#if>
                            <#else>
                                "
                                value="Додати в обране"
                            </#if>
                            />
                        </form>

                    </div>

                </div>
            </div>
        </div>
        <div class="container" style="margin-top: 10rem">
            <#if user.email??>
                <form accept-charset="UTF-8" action="/feedbacks/${house.id}" method="post">
            <textarea
                    class="form-control animated"
                    cols="50"
                    id="new-review"
                    name="description"
                    placeholder="Ваш відгук"
                    rows="5"
            ></textarea>

                    <div class="text-right mt-2">
                        <input
                                id="rating"
                                name="rating"
                                placeholder="0/10"
                                type="number"
                                step="1"
                                min="0"
                                max="10"
                        />
                        <button class="btn btn-success btn-lg" type="submit">
                            Зберегти
                        </button>
                    </div>
                </form>
            </#if>
            <hr/>

            <#list feedbacks as feedback>
                <div class="row lead rounded bg-white text-dark mt-4">
                    <div class="btn btn-secondary"><#if feedback.rating??>Оцінка ${feedback.rating}</#if>
                        від <#if feedback.user.firstName??>${feedback.user.firstName}</#if>

                    </div>
                    <div class="text-start"><#if feedback.description??>${feedback.description}</#if></div>
                </div>
            </#list>

        </div>
    </div>
</main>
<div>

</div>

</body>
</html>
