let urlParams = new URLSearchParams(window.location.search);
let id = urlParams.get("id");
const { createApp } = Vue;

createApp({
  data() {
    return {
      clients: [],
      clientId: [],
      clientLogged: [],
      products: [],
      filteredProducts: [],
      categories: ["Suplements", "Equipment", "Clothes"],
      clothes: ["Men", "Women"],
      checkedCategory: "",
      inputRange: 0,
      searchFilterInput: "",
      isOpen: false,
      cartProducts: [],
      ids :[],
      quantitys: [],
      noStock: false,
      addCartAllert: false,
      subtotal: 0,
      taxEstimate: 0.11,
      isLoading : false,
      success : false,
      moneyFormat: new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
      }),
    };
  },
  created() {
    this.loadData();
    const localStorageData = JSON.parse(localStorage.getItem("productsInCart"));
    const priceProduct = JSON.parse(localStorage.getItem("subtotal"));
    if (priceProduct == null) {
      this.subtotal = 0;
    } else {
      this.subtotal = priceProduct;
    }

    if (localStorageData == null) {
      this.cartProducts = [];
    } else {
      this.cartProducts = localStorageData;
    }

  },
  methods: {
    deleteItem(product) {
      product.stockAux = product.stock;
      this.cartProducts.splice(this.cartProducts.indexOf(product), 1);
      this.subtotal = 0;
      this.cartProducts.forEach((product) => {
        this.subtotal += product.price * product.quantity;
      });
  
      localStorage.setItem("productsInCart", JSON.stringify(this.cartProducts));
      localStorage.setItem("subtotal", JSON.stringify(this.subtotal));
    },
    reduceQuantity(product) {
      if (product.quantity > 1) {
        product.quantity--;
        product.stockAux++;
        localStorage.setItem(
          "productsInCart",
          JSON.stringify(this.cartProducts)
        );
      } else {
        product.stockAux++;
        this.deleteItem(product);
      }

      this.subtotal = 0;
      this.cartProducts.forEach((product) => {
        this.subtotal += product.price * product.quantity;
      });

      localStorage.setItem("subtotal", JSON.stringify(this.subtotal));
    },
    cleanCart() {
      this.subtotal = 0;
      this.cartProducts = [];
      localStorage.setItem("productsInCart", JSON.stringify(this.cartProducts));
      localStorage.setItem("subtotal", JSON.stringify(this.subtotal));
    },
    noStockAllert() {
      this.addCartAllert = false;
      this.noStock = true;
      setTimeout(() => {
        this.noStock = false;
      }, 5000);
    },
    makePayment() {
      axios.post('/api/paymentmp', [{
        id: this.ids,
        quantity: this.quantitys,
      }])
      .then((response) => {
        window.location.href = response.data
    })
    .then((response =>{
      axios.post('/api/purchase', `ids=${this.ids}&paymentAuthorization=TRUE`)
    }))

    },
      addCart(product) {
      this.addCartAllert = true;
      this.noStock = false;
      setTimeout(() => {
        this.addCartAllert = false;
      }, 3000);
      if (this.cartProducts.includes(product)) {
        product.quantity++;
        product.stockAux--;
        localStorage.setItem(
          "productsInCart",
          JSON.stringify(this.cartProducts)
        );
        if (product.stockAux < 0) {
          this.noStockAllert();
        }
      } else {
        product.stockAux = product.stock;
        product.quantity = 1;
        product.stockAux--;
        this.cartProducts.push(product);
        localStorage.setItem(
          "productsInCart",
          JSON.stringify(this.cartProducts)
        );
      }
      this.subtotal = 0;
      this.cartProducts.forEach((product) => {
        this.subtotal += product.price * product.quantity;
      });
      localStorage.setItem("subtotal", JSON.stringify(this.subtotal));
      console.log(this.cartProducts, this.subtotal);
    },
    loadData() {
      axios
        .get("/api/products")
        .then((response) => {
          this.products = response.data;
          console.log(cartProducts);
          this.filteredProducts = this.products;
          this.featureds = this.products.filter(product => product.stock < 5)
          this.cartProducts.forEach((product) => {
            this.ids = this.product.id
            this.quantitys = this.product.quantity
            console.log(this.product.id);
            console.log(this.product.quantity);
          });
        })
        .catch((error) => error);
    },
    filtrar(){
      this.cartProducts.forEach((product) => {
        this.ids = product.id
        this.quantitys = product.quantity
        console.log(this.ids);
        console.log(this.quantitys);
      })
    },
  },
  computed() {
    this.filtrar();
  }
}).mount("#app");
 