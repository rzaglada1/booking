<#import '../macros/head_footer.ftlh' as h>

<@h.head>


<main
        class="form-signin bg-dark text-center d-flex justify-content-center align-items-center view"
>

    <form method="post"
          action="/houses<#if house??>/${house.id?c}</#if>" enctype="multipart/form-data" class="w-75">


        <div class="row">
            <div class="col">
                <h1 class="h3 mb-3 fw-normal">Інформація про житло</h1>

                <div class="form-floating mb-2">
                    <input
                            type="text"
                            maxlength="50"
                            class="form-control"
                            id="floatingName"
                            name="name"
                            placeholder="Назва"
                            <#if house??>
                                value="${house.name}"
                            </#if>
                    />
                    <label for="floatingName">Назва</label>
                </div>

                <div class="form-group mb-2">
                    <label for="floatingDescription">Опис житла</label>
                    <textarea
                            maxlength="520"
                            rows="6"
                            class="form-control"
                            id="floatingDescription"
                            name="description"
                            placeholder="Опис житла"><#if house??>${house.description}</#if></textarea>
                </div>

                <div class="form-check mb-2">
                    <input
                            type="checkbox"
                            class="form-check-input"
                            id="floatingAvailable"
                            name="isAvailableForm"

                            <#if house??>
                                <#if house.isAvailable == true>checked</#if>
                                <#else>
                                checked
                            </#if>

                    />
                    <label class="text-light" for="floatingAvailable">Доступний для бронювання</label>
                </div>

                <div class="form-floating mb-2">
                    <input
                            type="number"
                            min="1"
                            class="form-control"
                            id="numTourists"
                            name="numTourists"
                            <#if house??>
                                value="${house.numTourists}"
                            </#if>
                            placeholder="Кількість місць"
                    />
                    <label for="numTourists">Кількість місць</label>
                </div>

                <div class="form-floating mb-2">
                    <input
                            type="number"
                            min="0"
                            step="0.01"
                            class="form-control"
                            id="priceF"
                            name="price"
                            <#if house??>
                                value="${house.price?c}"
                            </#if>
                            placeholder="Ціна бронювання"
                    />
                    <label for="priceF">Ціна бронювання</label>
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
                            <#if address.country??>
                                value="${address.country}"
                            </#if>
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
                            <#if address.city??>
                                value="${address.city}"
                            </#if>
                            placeholder="Місто"
                    />
                    <label for="floatingCity1">Місто</label>
                </div>

                <div class="row">
                    <div class="col">
                        <div class="form-floating mb-2">
                        <input
                                type="text"
                                maxlength="50"
                                class="form-control"
                                name="street"
                                <#if address.street??>
                                    value="${address.street}"
                                </#if>
                                placeholder="Вулиця"/>
                        <label for="floatingStreet1">Вулиця</label>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-floating mb-2">
                        <input
                                type="text"
                                maxlength="50"
                                class="form-control"
                                name="number"
                                <#if address.number??>
                                    value="${address.number}"
                                </#if>
                                placeholder="Будинок"
                        />
                            <label for="floatingNumber1">Будинок</label>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-floating mb-2">
                        <input
                                type="text"
                                maxlength="50"
                                class="form-control"
                                name="apartment"
                                <#if address.apartment??>
                                    value="${address.apartment}"
                                </#if>
                                placeholder="Квартира"
                        />
                            <label for="floatingApartment1">Квартира</label>
                        </div>
                    </div>
                </div>
            </div>

            <br>

            <div class="form-group">
                <label for="file"><b>Фото</b></label>
                <input name="file" type="file"
                       accept=".png, .jpg, .jpeg" class="form-control">
                <#if house??>
                    <#if house.image??>
                    <img class="m-5" src="/images/${house.image.getId()}" alt="No photo">
                    <#else>
                    <img src="" alt="No photo">
                    </#if>
                </#if>
            </div>

            <br>
            <br>
            <br>

            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-warning"><b>Зберегти</b></button>
                <a href="/houses" class="btn btn-danger"><b>Відмінити</b></a>
            </div>

        </div>
    </form>
</main>


</@h.head>
