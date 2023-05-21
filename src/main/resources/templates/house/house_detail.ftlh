<#import '../macros/head_footer.ftlh' as h>

<@h.head>

    <main
            class="px-3 text-center text-white bg-dark d-flex align-items-center justify-content-center flex-column view"
    >
        <div class="container py-4 my-4 mx-auto d-flex flex-column">
            <div class="header">
                <div class="d-flex justify-content-around" style="gap: 415px">
                    <div>
                        <h1>
                            <#if house??>
                                ${house.name}
                            <#else>
                                Назва
                            </#if>
                        </h1>
                    </div>
                    <div style="gap: 15px" class="d-flex justify-content-between">
                        <form action="/wishes/${house.id}/new" method="post">
                            <input type="submit" style="height: 58px" class="btn btn-info

                                   <#if user??>
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
                        <div class="d-flex flex-column">
                            <div
                                    class="btn btn-secondary fs-3"
                            >
                                <#if averRating == -1>без оцінок<#else>${averRating}</#if>
                            </div>
                            <p class="text-right para">
                                <#if countFeedback != 0>На основі ${countFeedback} відгуків</#if></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-body mt-4">
                <div class="row">
                    <div class="col-md-5">
                        <#if house??>
                            <#if house.image??>
                                <img src="/images/${house.image.getId()}" alt="No photo" width="90%" height="95%">
                            <#else>
                                <img src="" alt="No photo">
                            </#if>
                        </#if>
                    </div>
                    <div class="col-md-7 p-1 d-flex flex-column border rounded position-relative">
                        <ul style="padding-left: 0; ">
                            <li style="display: inline;font-weight: bold;">Опис житла:<br></li>
                            <li style="display: inline;"><#if house??>${house.description}</#if>
                                <hr align="center" class="cardHr">
                            </li>
                            <li style="display: inline;font-weight: bold;">Адреса:<br></li>
                            <li style="display: inline;">
                                <#if house.address.country??>${house.address.country},</#if>
                                <#if house.address.city??>${house.address.city},</#if>
                                <#if house.address.street?has_content>вул.${house.address.street},</#if>
                                <#if house.address.number?has_content>буд.${house.address.number},</#if>
                                <#if house.address.apartment?has_content>кв.${house.address.apartment}.</#if>
                                <hr class="cardHr">
                            </li>
                        </ul>
                        <form class="w-100 d-flex justify-content-around align-items-stretch"
                              style="margin-top: auto; gap: 10px"
                              action="/houses/${house.id}/prebooking" method="post">
                            <div class="form-floating">
                                <input
                                        type="date"
                                        class="form-control <#if dataBookingStartError??>is-invalid</#if>"
                                        id="dateBookingStart"
                                        name="dataBookingStart"
                                        <#if orderHistory?? && orderHistory.dataBookingStart??>
                                            value="${orderHistory.dataBookingStart}"</#if>
                                />
                                <label class="text-dark" for="dateBookingStart">Дата заїзду</label>
                                <#if dataBookingStartError??>
                                    <div class="invalid-feedback">
                                        ${dataBookingStartError}
                                    </div>
                                </#if>
                            </div>


                            <div class="form-floating">
                                <input
                                        type="number"
                                        class="form-control <#if numDaysBookingError??>is-invalid</#if>"
                                        id="numDaysBooking"
                                        name="numDaysBooking"
                                        <#if orderHistory?? && orderHistory.numDaysBooking??>
                                            value="${orderHistory.numDaysBooking}"<#else>value="1"</#if>
                                />
                                <label class="text-dark" for="numDaysBooking">На скільки днів</label>
                                <#if numDaysBookingError??>
                                    <div class="invalid-feedback">
                                        ${numDaysBookingError}
                                    </div>
                                </#if>
                            </div>
                            <div class="form-floating">
                                <input
                                        type="number"
                                        class="form-control <#if numTouristsError??>is-invalid</#if>"
                                        id="numTourists"
                                        name="numTourists"
                                        <#if orderHistory?? && orderHistory.numTourists??>
                                            value="${orderHistory.numTourists}" <#else>value="2"</#if>
                                />
                                <label class="text-dark" for="numTourist">Кількість людей</label>
                                <#if numTouristsError??>
                                    <div class="invalid-feedback">
                                        ${numTouristsError}
                                    </div>
                                </#if>
                            </div>
                            <div class="form-floating">
                                <input type="submit" class="btn btn-warning" style="height: 58px" value="Бронювати">
                            </div>
                        </form>
                    </div>
                    <div class="row mt-5">
                        <div class="col-md-5 border rounded position-relative"
                             style="height: 200px; overflow-y: scroll">
                            <ul style="padding-left: 0; ">
                                <li class="mb-4" style="display: inline;font-weight: bold;">Календар доступності<br>
                                </li>
                                <#if orders??>
                                    <#list orders as order>
                                        <li style="display: inline;"> Заброньовано з: ${order.dataBookingStart}
                                            до: ${order.dataBookingEnd}
                                            <hr class="cardHr">
                                        </li>
                                    </#list>
                                <#else>
                                    <li style="display: inline;">
                                        всі дати доступні
                                        <hr class="cardHr">
                                    </li>
                                </#if>

                            </ul>
                        </div>
                    </div>
                </div>
            </div>


            <div class="container">
                <#if user??>
                    <form accept-charset="UTF-8" action="/houses/${house.id}/feedbacks" method="post">
            <textarea
                    class="form-control animated <#if descriptionError??>is-invalid</#if>"
                    cols="50"
                    id="new-review"
                    name="description"
                    placeholder="Ваш відгук"
                    rows="5"
            ></textarea>
                        <#if descriptionError??>
                            <div class="invalid-feedback">
                                ${descriptionError}
                            </div>
                        </#if>

                        <div class="text-right mt-2">
                            <input
                                    id="rating"
                                    name="rating"
                                    placeholder="1/10"
                                    type="number"
                                    value=10
                                    step="1"
                                    min="1"
                                    max="10"
                            />
                            <button class="btn btn-warning btn-lg" type="submit">
                                Зберегти
                            </button>
                        </div>
                    </form>
                </#if>
                <hr/>

                <div class=" border rounded position-relative" style="height: 400px; overflow-y: scroll">
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
        </div>
    </main>
    <div>

    </div>

</@h.head>
