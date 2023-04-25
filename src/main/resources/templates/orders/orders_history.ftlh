<#import '../macros/head_footer.ftlh' as h>
<#import '../macros/pager.ftlh' as p>

<@h.head>

<div class="album py-5 bg-dark">
    <div class="container">
        <div
                class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-2 row-cols-xl-3 g-3"
        >
            <#list page.content as order>
                <div class="col">

                    <div class="card shadow-sm h-100">
                        <img
                                src="<#if order.house.image?? >/images/${order.house.image.getId()}</#if>"
                                alt="No photo"
                                class="bd-placeholder-img card-img-top"
                                style="background-size: cover; width: 100%; height: 320px"
                        />

                        <div class="card-body d-flex flex-column">
                            <p class="card-text fw-bold h3"><#if order.house.name??>${order.house.name} <#else>Назва</#if></p>

                            <p class="card-text text-break fs-7">Локація<br>
                                <#if order.house.address.country??>${order.house.address.country},</#if>
                                <#if order.house.address.city??>${order.house.address.city},</#if>
                                <#if order.house.address.street?has_content>вул.${order.house.address.street},</#if>
                                <#if order.house.address.number?has_content>буд.${order.house.address.number},</#if>
                                <#if order.house.address.apartment?has_content>кв.${order.house.address.apartment}.</#if>
                            </p>

                            <p class="card-text text-break fs-7">
                                Опис<br><#if order.house.description??>${order.house.description}</#if></p>


                            <form class="mt-auto productForm" action="">

                                <span class="d-flex align-items-center justify-content-between mt-2">
                                    <span class="fw-bold fs-6 priceDisc product text-warning"
                                          style="flex-grow: 2">Дата бронювання:</span>
                                    <span class=" fs-5 price product" style="flex-grow: 2">
                                    <#if order.dateCreate??>${order.dateCreate.toLocalDate()} <#else>невідомо</#if>
                                    </span>
                                </span>

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
                        <#if order.house.price??>${order.price} <#else>невідомо</#if> грн
                    </span>

                  </span>


                            </form>


                        </div>

                        <div class="d-flex flex-column btn-group text-center">
                            <a href="/houses/${order.house.id}/detail"
                               class="btn fw-bold btn-sm btn-warning text-dark mb-1 mx-5 py-2 rounded "
                            >
                                Забронювати знову
                            </a>

                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>

    <div class="d-flex mt-5 justify-content-center" >
        <@p.pager url page/>
    </div>

</div>

</@h.head>