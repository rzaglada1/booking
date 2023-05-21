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
                            class="form-control <#if numTouristsError??>is-invalid</#if>"
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
                    <#if numTouristsError??>
                        <div class="invalid-feedback">
                            ${numTouristsError}
                        </div>
                    </#if>
                </div>

                <div class="form-floating mb-2">
                    <input
                            type="number"
<#--                            min="0"-->
                            step="0.01"
                            class="form-control <#if priceError??>is-invalid</#if>"
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
                    <#if priceError??>
                        <div class="invalid-feedback">
                            ${priceError}
                        </div>
                    </#if>
                </div>

            </div>

            <div class="col">
                <h1 class="h3 mb-3 fw-normal">Адреса</h1>

                <div class="form-floating mb-2">
                    <input
                            type="text"
                            maxlength="50"
                            class="form-control <#if address\.countryError??>is-invalid</#if>"
                            id="floatingCountry1"
                            name="address.country"
                            <#if address.country??>
                                value="${address.country}"
                            </#if>
                            placeholder="Країна"
                    />
                    <label for="floatingCountry1">Країна</label>
                    <#if address\.countryError??>
                        <div class="invalid-feedback">
                            ${address\.countryError}
                        </div>
                    </#if>
                </div>

                <div class="form-floating mb-2">
                    <input
                            type="text"
                            maxlength="50"
                            class="form-control <#if address\.cityError??>is-invalid</#if>"
                            id="floatingCity1"
                            name="address.city"
                            <#if address.city??>
                                value="${address.city}"
                            </#if>
                            placeholder="Місто"
                    />
                    <label for="floatingCity1">Місто</label>
                    <#if address\.cityError??>
                        <div class="invalid-feedback">
                            ${address\.cityError}
                        </div>
                    </#if>
                </div>

                <div class="row">
                    <div class="col">
                        <div class="form-floating mb-2">
                        <input
                                type="text"
                                maxlength="50"
                                class="form-control <#if address\.streetError??>is-invalid</#if>"
                                name="address.street"
                                <#if address.street??>
                                    value="${address.street}"
                                </#if>
                                placeholder="Вулиця"/>
                        <label for="floatingStreet1">Вулиця</label>
                            <#if address\.streetError??>
                                <div class="invalid-feedback">
                                    ${address\.streetError}
                                </div>
                            </#if>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-floating mb-2">
                        <input
                                type="text"
                                maxlength="50"
                                class="form-control <#if address\.numberError??>is-invalid</#if>"
                                name="address.number"
                                <#if address.number??>
                                    value="${address.number}"
                                </#if>
                                placeholder="Будинок"
                        />
                            <label for="floatingNumber1">Будинок</label>
                            <#if address\.numberError??>
                                <div class="invalid-feedback">
                                    ${address\.numberError}
                                </div>
                            </#if>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-floating mb-2">
                        <input
                                type="text"
                                maxlength="50"
                                class="form-control "
                                name="address.apartment"
                                <#if address.apartment??>
                                    value="${address.apartment}"
                                </#if>
                                placeholder="Квартира"
                        />
                            <label for="floatingApartment1">Квартира</label>
                        </div>
                    </div>
                    <input
                            type="hidden"
                            maxlength="50"
                            class="form-control "
                            name="imageId"
                            <#if house?? && house.imageId??>value="${house.imageId}"</#if>
                    />
                </div>
            </div>

            <br>

            <div class="form-group">
                <label for="file"><b>Фото</b></label>

                <input name="file" type="file"
                       accept=".png, .jpg, .jpeg" class="form-control <#if image\.photoToBytesError??>is-invalid</#if>">
                <#if house??>
                    <#if house.imageId??>
<#--                    <img class="m-5" src="/images/${house.image.getId()}" alt="No photo">-->
                        <img class="m-5" src="/images/${house.imageId?c}"  alt="No photo">
                    <#else>
                    <img src="" alt="No photo">
                    </#if>
                </#if>
                <#if image\.photoToBytesError??>
                    <div class="invalid-feedback">
                        Please, select a photo
                    </div>
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
