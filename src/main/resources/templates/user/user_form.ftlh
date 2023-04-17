<#import '../macros/head_footer.ftlh' as h>

<@h.head>

<main>
    <div class="card-body bg-dark d-flex justify-content-center  ">



            <form method="post" action="/users<#if user?? && !errorMessage??>/update</#if>

<#if user?? && !errorMessage?? && admin??>/${user.id}</#if>


" style="width: 400px">


                <h1 class="h3 mb-3 text-light fw-normal"><#if user?? && !errorMessage??>Edit user<#else>Create user</#if></h1>

                <#if user?? && admin??>
                    <#list user.roles as role>
                        <div class="form-floating mb-2">
                            <select class="form-control" id="formControlSelectRole" name="roleForm">
                                <#if user.roles??>
                                    <#list user.roles as rol>
                                        <option>${rol}</option>
                                    </#list>
                                </#if>

                                <#if rolesEnum??>
                                    <#list rolesEnum as roleEnum>
                                        <option>${roleEnum}</option>
                                    </#list>
                                </#if>
                            </select>
                            <label for="formControlSelectRole">Role select</label>
                        </div>
                    </#list>
                </#if>


                <div class="form-floating mb-2">
                    <input id="floatingEmail" type="text" maxlength="50" class="form-control" placeholder="Email"
                            <#if user?? && !errorMessage??>
                                value="${user.email}" readonly
                            </#if>
                            <#if user?? && errorMessagePass??>
                                value="${user.email}"
                            </#if>
                           name="email">
                    <label for="floatingEmail">Email</label>
                </div>

                <div class="form-floating mb-4">
                    <#if user??>
                        <#if errorMessageDouble??>
                            <p class="text-danger">${errorMessageDouble}</p>
                        </#if>
                    </#if>
                </div>


                <#if user?? && !errorMessage??>
                    <div class="form-floating mb-2">
                        <input id="floatingPasswordIld" type="password" maxlength="50" class="form-control"
                               placeholder="Password"
                               name="passwordOld">

                        <label for="floatingPasswordIld">For change, enter old password</label>
                    </div>

                    <div class="form-floating mb-2">
                        <input id="floatingPasswordNew" type="password" maxlength="50" class="form-control"
                               placeholder="PasswordNew"
                               name="password">
                        <label for="floatingPasswordNew">New password</label>
                    </div>


                    <div class="form-floating mb-2">
                        <input id="floatingPasswordConfirm" type="password" maxlength="50" class="form-control"
                               placeholder="Password confirm"

                               name="passwordConfirm">
                        <label for="floatingPasswordConfirm">New password confirm</label>
                    </div>

                <#else>
                    <div class="form-floating mb-2">
                        <input id="floatingPassword" type="password" maxlength="50" class="form-control"
                               placeholder="Password"
                               name="password">
                        <label for="floatingPassword">Password</label>
                    </div>

                    <div class="form-floating mb-2">
                        <input id="floatingPasswordConfirm" type="password" maxlength="50" class="form-control"
                               placeholder="Password confirm"
                               name="passwordConfirm">
                        <label for="floatingPasswordConfirm">Password confirm</label>
                    </div>
                    <#if  errorMessagePass??>
                        <p class="text-danger">${errorMessagePass}</p>
                    </#if>
                </#if>


                <div class="form-floating mb-2">
                    <input id="floatingName" type="text" maxlength="50" class="form-control"
                           name="firstName" placeholder="First name"
                            <#if user??>
                                value="${user.firstName}"
                            </#if> >

                    <label for="floatingName">First name</label>
                </div>

                <div class="form-floating mb-2">
                    <input id="floatingLastname" type="text" maxlength="50" class="form-control"
                           name="lastName" placeholder="Last name"
                            <#if user??>
                                value="${user.lastName}"
                            </#if>
                    >
                    <label for="floatingLastname">Last Name</label>
                </div>

                <div class="form-floating mb-2">
                    <input id="floatingPhone" type="tel" maxlength="15" class="form-control"
                           name="phone" placeholder="Phone"
                            <#if user??>
                                value="${user.phone}"
                            </#if>
                    >
                    <label for="floatingPhone">Phone</label>
                </div>

                <#if admin??>
                    <div class="form-check">
                        <input id="flexCheckChecked" class="form-check-input" type="checkbox"
                               name="activeForm"

                               <#if user.active>checked</#if> >
                        <label class="form-check-label text-light" for="flexCheckChecked">
                            Active
                        </label>
                    </div>
                <#else>
                    <div class="form-floating mb-4">
                        <#if user?? && !errorMessage??>
                            <#if user.active>active<#else>not active</#if>
                        </#if>
                    </div>
                </#if>


                <button class="w-100 btn btn-lg btn-warning"
                        type="submit"><#if user?? && !errorMessage??>Оновити<#else>Створити</#if></button>

                <#if user?? && !errorMessage??>
                    <a href="/users/delete<#if admin??>/${user.id}</#if>"
                       class="btn w-100  btn-lg btn-danger ">
                        Видалити
                    </a>
                </#if>

            </form>
        </div>


</main>

</@h.head>
