<#import '../macros/head_footer_style.ftlh' as hs>
<#import '../macros/pager.ftlh' as p>

<@hs.headStyle>


<div class="album py-5 bg-dark">
    <div class="container">
        <div
                class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-2 row-cols-xl-3 g-3"
        >
            <#list page.content as userParam>
                <div class="col">
                    <div class="card shadow-sm h-100">

                        <div class="card-body d-flex flex-column">

                            <form method="get" action="/users/edit/${userParam.id}" style="width: 400px">

                                <#if userParam??>
                                    <#list userParam.roles as role>
                                <div class="form-floating mb-2">
                                    <input type="text" maxlength="50" class="form-control" placeholder="id"
                                            value="${role}"
                                           name="role"
                                           readonly>
                                    <label for="floatingEmail">role</label>
                                </div>
                                    </#list>
                                </#if>

                                <div class="form-floating mb-2">
                                    <input type="text" maxlength="50" class="form-control" placeholder="id"
                                            <#if userParam??>
                                                value="${userParam.id}"
                                            </#if>
                                           name="id"
                                           readonly>
                                    <label for="floatingEmail">id</label>
                                </div>

                                <div class="form-floating mb-2">
                                    <input type="text" maxlength="50" class="form-control" placeholder="Email"
                                            <#if userParam??>
                                                value="${userParam.email}"
                                            </#if>
                                           name="email"
                                           readonly>
                                    <label for="floatingEmail">Email</label>
                                </div>



                                <div class="form-floating mb-2">
                                    <input type="text"  maxlength="50" class="form-control"
                                           name="firstName" placeholder="First name"
                                            <#if userParam??>
                                                value="${userParam.firstName}"
                                            </#if>
                                           readonly>

                                    <label for="floatingName">First name</label>
                                </div>

                                <div class="form-floating mb-2">
                                    <input type="text" maxlength="50"  class="form-control"
                                           name="lastName" placeholder="Last name"
                                            <#if userParam??>
                                                value="${userParam.lastName}"
                                            </#if>
                                           readonly>
                                    <label for="floatingSurname">Last Name</label>
                                </div>

                                <div class="form-floating mb-2">
                                    <input type="tel" maxlength="15" class="form-control"
                                           name="phone" placeholder="Phone"
                                            <#if userParam??>
                                                value="${userParam.phone}"
                                            </#if>
                                           readonly>
                                    <label for="floatingPhone">Phone</label>
                                </div>

                                <div class="form-floating mb-4">
                                    <#if userParam??>
                                        <#if userParam.active>active<#else>not active</#if>
                                    </#if>
                                </div>

                                <button class="w-100 btn btn-lg btn-warning" type="submit">Деталі</button>

                            </form>

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

</@hs.headStyle>