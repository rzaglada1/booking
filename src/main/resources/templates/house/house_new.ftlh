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
    <title>New house</title>
</head>
<body>
<main
        class="form-signin text-center d-flex justify-content-center align-items-center view"
>
    <form method="post" action="/houses" enctype="multipart/form-data" class="w-75">
        <div class="row">
            <div class="col">
                <h1 class="h3 mb-3 fw-normal">Інформація про житло</h1>

                <div class="form-check mb-2">
                    <input
                            type="checkbox"
                            class="form-check-input"
                            id="floatingAvailable"
                            name="isAvailable"
                    />
                    <label for="floatingAvailable">Доступний для бронювання</label>
                </div>

                <div class="form-floating mb-2">
                    <input
                            type="text"
                            maxlength="50"
                            class="form-control"
                            id="floatingName"
                            name="name"
                            placeholder="Назва"
                    />
                    <label for="floatingName">Назва</label>
                </div>

                <div class="form-floating mb-2">
              <textarea
                      maxlength="520"
                      rows="17"
                      class="form-control"
                      id="floatingDescription"
                      name="description"
                      placeholder="Опис житла"
              ></textarea>
                  <label for="floatingDescription">Опис житла</label>

                </div>

            </div>

            <div class="col">
                <h1 class="h3 mb-3 fw-normal">Адреса</h1>

                <div class="form-floating mb-2">
                    <input
                            type="text"
                            maxlength="50"
                            class="form-control"
                            id="floatingCountry1"
                            name="country"
                            placeholder="Країна"
                    />
                    <label for="floatingCountry1">Країна</label>
                </div>

                <div class="form-floating mb-2">
                    <input
                            type="text"
                            maxlength="50"
                            class="form-control"
                            id="floatingCity1"
                            name="city"
                            placeholder="Місто"
                    />
                    <label for="floatingCity1">Місто</label>
                </div>

                <div class="row">
                    <div class="col">
                        <input
                                type="text"
                                maxlength="50"
                                class="form-control"
                                name="street"
                                placeholder="Вулиця"
                        />
                    </div>
                    <div class="col">
                        <input
                                type="text"
                                maxlength="50"
                                class="form-control"
                                name="number"
                                placeholder="Номер будівлі"
                        />
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col">
                  <div class="form-floating mb-2">
                    <input
                            type="number"
                            min="1"
                            class="form-control"
                            id="numTourists"
                            name="numTourists"
                            placeholder="Кількість місць"
                    />
                    <label for="numTourists">Кількість місць</label>
                  </div>

                </div>

                <div class="col">
                  <div class="form-floating mb-2">
                    <input
                            type="number"
                            min="0"
                            step="0.01"
                            class="form-control"
                            id="price"
                            name="price"
                            placeholder="Ціна бронювання"
                    />
                    <label for="price">Ціна бронювання</label>
                  </div>
                </div>
            </div>


            <br>

            <div class="form-group">
                <label for="file"><b>Фото</b></label>
                <input name="file" type="file" accept=".png, .jpg, .jpeg" class="form-control">
            </div>

            <br>
            <br>
            <br>

            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-success"><b>Зберегти</b></button>
                <a href="" class="btn btn-danger"><b>Відмінити</b></a>
            </div>

        </div>
    </form>
</main>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"
></script>

</body>
</html>
