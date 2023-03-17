const addrs = document.querySelector(".addresses");
const tables = document.querySelector(".tables");
let addrCount = 2;
let tableCount = 2;

document.querySelector(".addressBtn").addEventListener("click", (e) => {
  e.preventDefault();
  const addrInp = document.createElement("div");
  addrInp.classList = `addr${addrCount}`;
  addrInp.innerHTML = `
            <div class="form-floating mb-2 mt-3">
              <input
                type="text"
                maxlength="50"
                class="form-control"
				id="floatingCountry${addrCount}"
                name="country${addrCount}"
                placeholder="Країна"
              />
              <label for="floatingCountry${addrCount}">Країна</label>
            </div>

            <div class="form-floating mb-2">
              <input
                type="text"
                maxlength="50"
                class="form-control"
                id="floatingCity${addrCount}"
                name="city${addrCount}"
                placeholder="Місто"
              />
              <label for="floatingCity${addrCount}">Місто</label>
            </div>

            <div class="row">
              <div class="col">
                <input
                  type="text"
                  maxlength="50"
                  class="form-control"
                  name="street${addrCount}"
                  placeholder="Вулиця"
                />
              </div>
              <div class="col">
                <input
                  type="text"
                  maxlength="50"
                  class="form-control"
                  name="num${addrCount}"
                  placeholder="Номер будівлі"
                />
              </div>
            </div>
            <button type="button" class="deleteAddress${addrCount}Btn w-100 mt-4 btn btn-danger" onclick="deleteAddr('addr${addrCount}')">
              Видалити адресу
            </button>
`;
  addrs.append(addrInp);
  addrCount++;
});

document.querySelector(".tableBtn").addEventListener("click", (e) => {
  e.preventDefault();
  const tableInp = document.createElement("div");
  tableInp.classList = `table${tableCount}`;
  tableInp.innerHTML = `
            <div class="form-floating mb-2 mt-3">
              <input
                type="text"
                maxlength="50"
                class="form-control"
                id="floatingTableName${tableCount}"
                name="tableName${tableCount}"
                placeholder="Назва столу"
              />
              <label for="floatingTableName${tableCount}">Назва столу</label>
            </div>

            <div class="form-group mb-2">
              <textarea
                maxlength="520"
                rows="7"
                class="form-control"
                name="tableDescription${tableCount}"
                placeholder="Опис столу"
              ></textarea>
            </div>

            <div class="row">
              <div class="col">
                <input
                  type="number"
                  min="1"
                  class="form-control"
                  name="tableSeats${tableCount}"
                  placeholder="Кількість місць"
                />
              </div>
              <div class="col">
                <input
                  type="number"
                  min="0"
                  step="0.01"
                  class="form-control"
                  name="tablePrice${tableCount}"
                  placeholder="Ціна бронювання"
                />
              </div>
            </div>
            <button type="button" class="deleteTable${tableCount}Btn w-100 mt-4 btn btn-danger" onclick="deleteTable('table${tableCount}')">
              Видалити стіл
            </button>
		`;
  tables.append(tableInp);
  tableCount++;
});

function deleteAddr(className) {
	addrs.removeChild(document.querySelector(`.${className}`))
}

function deleteTable(className) {
	tables.removeChild(document.querySelector(`.${className}`))
}
