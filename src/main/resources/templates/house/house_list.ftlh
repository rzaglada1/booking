<#import '../macros/head_footer.ftlh' as h>
<#import '../macros/pager.ftlh' as p>

<@h.head>



<div class="album py-5 bg-dark">
    <div class="container">
        <div
                class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-2 row-cols-xl-3 g-3"
        >
            <#list page.content as house>
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

                        <p class="card-text text-break fs-7">Опис<br><#if house.description??>${house.description}</#if></p>

                    </div>

                    <div class="d-flex flex-column btn-group text-center">
                        <a
                                href="/houses/${house.id}/edit"
                                class="btn fw-bold btn-sm btn-warning text-dark mb-1 mx-5 py-2 rounded"
                        >Змінити</a>
                        <a
                                href="/houses/${house.id}/delete"
                                class="btn fw-bold btn-sm btn-dark mb-3 mx-5 py-2 rounded"
                        >Видалити</a
                        >
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