<#import '../macros/head_footer_style.ftlh' as hs>

<@hs.headStyle>


<div class="album py-5 bg-dark d-flex align-items-center justify-content-center flex-column view">
    <div class="container">
        <div
                class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-2 row-cols-xl-3 g-3"
        >

            <div class="col mx-auto">

                <div class="card shadow-sm h-100">

                    <div class="card-body d-flex flex-column">
                        <p class="card-text fw-bold h3"><#if house.name??>${house.name} <#else>Назва</#if></p>

                        <p class="card-text text-break fs-7">Локація<br>
                            <#if house.address.country??>${house.address.country},</#if>
                            <#if house.address.city??>${house.address.city},</#if>
                            <#if house.address.street?has_content>вул.${house.address.street},</#if>
                            <#if house.address.number?has_content>буд.${house.address.number},</#if>
                            <#if house.address.apartment?has_content>кв.${house.address.apartment}.</#if>
                        </p>


                        <form class="mt-auto productForm" action="/houses/${house.id}/booking" method="post">

                            <span class="d-flex align-items-center justify-content-between mt-2">
                                    <span class="fw-bold fs-6 priceDisc product text-warning"
                                          style="flex-grow: 2">Початок: </span>
                                    <span class="fw-bold fs-5 price product" style="flex-grow: 2">
                                    <#if order.dataBookingStart??>${order.dataBookingStart}
                                    <#else>невідомо</#if>
                                    </span>
                                </span>

                            <span class="d-flex align-items-center justify-content-between mt-2">
                                    <span class="fw-bold fs-6 priceDisc product text-warning"
                                          style="flex-grow: 2">Виїзд: </span>
                                    <span class="fw-bold fs-5 price product" style="flex-grow: 2">
                                    <#if order.dataBookingEnd??>${order.dataBookingEnd}
                                    <#else>невідомо</#if>
                                    </span>
                                </span>

                            <span class="d-flex align-items-center justify-content-between mt-2">
                                    <span class="fw-bold fs-6 priceDisc product text-warning"
                                          style="flex-grow: 2">Скільки нас буде: </span>
                                    <span class="fw-bold fs-5 price product" style="flex-grow: 2">
                                    <#if order.numTourists??>${order.numTourists}
                                    <#else>невідомо</#if>
                                    </span>
                                </span>


                            <span class="d-flex align-items-center justify-content-between mt-2">

                    <span
                            class="fw-bold fs-4 priceDisc product text-warning"
                            style="flex-grow: 2"
                    >
                        Загальна вартість
                    </span>
                    <span
                            class="fw-bold fs-4 price product"
                            style="flex-grow: 2"
                    >
                        <#if order.price??>${order.price} <#else>невідомо</#if> грн
                    </span>

                  </span>
                            <input type="hidden" name="dataBookingStart" value="${order.dataBookingStart}">
                            <input type="hidden" name="dataBookingEnd" value="${order.dataBookingEnd}">
                            <input type="hidden" name="numTourists" value="${order.numTourists}">

                            <div class="d-flex flex-column btn-group text-center">
                                <input type="submit"  class="btn fw-bold btn-sm btn-warning text-dark mb-1 mx-5 py-2 rounded "
                                       value="Бронювати">

                                <a href="/houses/${house.id}/detail"
                                   class="btn fw-bold btn-sm btn-danger text-dark mb-1 mx-5 py-2 rounded "
                                >
                                    Відмінити
                                </a>

                            </div>

                        </form>


                    </div>


                </div>
            </div>

        </div>
    </div>

</div>

</@hs.headStyle>