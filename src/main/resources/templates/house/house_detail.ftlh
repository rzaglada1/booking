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
        a {
            text-decoration: none;
            color: #000;
        }
        .view {
            width: 100%;
            min-height: calc(100vh - 160px);
        }
        .no-border-radius {
            border-radius: 0;
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

        .fa-star {
            color: yellow;
        }

        li {
            list-style: none;
            line-height: 50px;
        }

        .col-md-2 {
            padding-bottom: 20px;
            font-weight: bold;
        }

        .col-md-2 a {
            text-decoration: none;
        }

        .col-md-2.mio {
            font-size: 12px;
            padding-top: 7px;
        }

        .des::after {
            content: ".";
            font-size: 0;
            display: block;
            border-radius: 20px;
            height: 6px;
            width: 55%;
            background: yellow;
            margin: 14px 0;
        }

        .r4 {
            padding-left: 25px;
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
                    7.8
                </div>
                <p class="text-right para">Based on 250 Review</p>
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
                <div class="col-md-7 p-0">
                    <ul>
                        <li>Опис житла: <br><#if house??>${house.description}</#if></li>
                        <li>Адреса: <br>
                            <#if house.address.country??>${house.address.country},</#if>
                            <#if house.address.city??>${house.address.city},</#if>
                            <#if house.address.street?has_content>вул.${house.address.street},</#if>
                            <#if house.address.number?has_content>буд.${house.address.number},</#if>
                            <#if house.address.apartment?has_content>кв.${house.address.apartment}.</#if>
                        </li>
                    </ul>
                    <a class="btn btn-info" href="/houses/${house.id}/wish" style="float: right"
                    >Додати в обране</a
                    >
                    <a class="btn btn-warning" href="/houses/${house.id}/booking"
                            style="float: right; margin-right: 10px"
                    >Бронювати</a
                    >
                </div>
            </div>
        </div>
        <div class="container" style="margin-top: 10rem">
            <form accept-charset="UTF-8" action="" method="post">
            <textarea
                    class="form-control animated"
                    cols="50"
                    id="new-review"
                    name="comment"
                    placeholder="Ваш відгук"
                    rows="5"
            ></textarea>

                <div class="text-right mt-2">
                    <input
                            id="rating"
                            name="rating"
                            placeholder="0.0/10.0"
                            type="number"
                            step="0.1"
                            min="0.0"
                    />
                    <button class="btn btn-success btn-lg" type="submit">
                        Зберегти
                    </button>
                </div>
            </form>
            <hr />
            <div class="row lead rounded bg-white text-dark mt-4">
                <div class="btn btn-secondary">7.8/10.0 від Користувач</div>
                <div class="text-start">Рейтинг</div>
            </div>
            <div class="row lead rounded bg-white text-dark mt-4">
                <div class="btn btn-secondary">7.8/10.0 від Користувач</div>
                <div class="text-start">Рейтинг</div>
            </div>
        </div>
    </div>
</main>
<div>

</div>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"
></script>
</body>
</html>
