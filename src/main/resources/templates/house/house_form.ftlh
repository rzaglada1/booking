<#import '../macros/head_footer_style.ftlh' as hs>

<@hs.headStyle>


<main
        class="form-signin bg-dark text-center d-flex justify-content-center align-items-center view"
>

    <form method="post"
          action="/houses<#if house?? && edit??>/${house.id?c}</#if>" enctype="multipart/form-data" class="w-75">


        <div class="row">
            <div class="col">
                <h1 class="h3 mb-3 fw-normal">Інформація про житло</h1>

                <div class="form-floating mb-2">
                    <input
                            type="text"
                            maxlength="50"
                            class="form-control <#if nameError??>is-invalid</#if>"
                            id="floatingName"
                            name="name"
                            placeholder="Назва"
                            <#if house??>
                                value="${house.name}"
                            </#if>
                    />
                    <label for="floatingName">Назва</label>
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </#if>
                </div>

                <div class="form-group mb-2">
                    <label for="floatingDescription">Опис житла</label>
                    <textarea
                            maxlength="520"
                            rows="6"
                            class="form-control <#if descriptionError??>is-invalid</#if>"
                            id="floatingDescription"
                            name="description"
                            placeholder="Опис житла"><#if house??>${house.description}</#if></textarea>

                    <#if descriptionError??>
                        <div class="invalid-feedback">
                            ${descriptionError}
                        </div>
                    </#if>

                </div>


                <div class="form-check mb-2">
                    <input
                            type="checkbox"
                            class="form-check-input"
                            id="floatingAvailable"
                            name="isAvailableForm"

                            <#if house??>
                                <#if house.isAvailable?? && house.isAvailable == true>checked</#if>
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
                                <#else >
                                value = 1
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
                                <#else>
                                value = 0
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
                            class="form-control <#if countryError??>is-invalid</#if>"
                            id="floatingCountry1"
                            name="country"
                            <#if address.country??>
                                value="${address.country}"
                            </#if>
                            placeholder="Країна"
                    />
                    <label for="floatingCountry1">Країна</label>
                    <#if countryError??>
                        <div class="invalid-feedback">
                            ${countryError}
                        </div>
                    </#if>
                </div>

                <div class="form-floating mb-2">
                    <input
                            type="text"
                            maxlength="50"
                            class="form-control <#if cityError??>is-invalid</#if>"
                            id="floatingCity1"
                            name="city"
                            <#if address.city??>
                                value="${address.city}"
                            </#if>
                            placeholder="Місто"
                    />
                    <label for="floatingCity1">Місто</label>
                    <#if cityError??>
                        <div class="invalid-feedback">
                            ${cityError}
                        </div>
                    </#if>
                </div>

                <div class="row">
                    <div class="col">
                        <div class="form-floating mb-2">
                        <input
                                type="text"
                                maxlength="50"
                                class="form-control <#if streetError??>is-invalid</#if>"
                                name="street"
                                <#if address.street??>
                                    value="${address.street}"
                                </#if>
                                placeholder="Вулиця"/>
                        <label for="floatingStreet1">Вулиця</label>
                            <#if streetError??>
                                <div class="invalid-feedback">
                                    ${streetError}
                                </div>
                            </#if>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-floating mb-2">
                        <input
                                type="text"
                                maxlength="50"
                                class="form-control <#if numberError??>is-invalid</#if>"
                                name="number"
                                <#if address.number??>
                                    value="${address.number}"
                                </#if>
                                placeholder="Будинок"
                        />
                            <label for="floatingNumber1">Будинок</label>
                            <#if numberError??>
                                <div class="invalid-feedback">
                                    ${numberError}
                                </div>
                            </#if>
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


</@hs.headStyle>
