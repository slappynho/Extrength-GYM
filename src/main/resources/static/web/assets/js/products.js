const app = Vue.createApp({
  data() {
    return {
      products: [],
      productsName: [],
      selectedFilter: "",
      otherFilter: "",
      supplementsFilter: [],
      clothFilter: [],
      equipmentFilter: [],
      id: new URLSearchParams(location.search).get("id"),
      textSearch: '',
      radio: [],
      categorias: [],
      backUp: [],
      numeroCarrito: 0,
    }
  },
  created() {
    this.loadData()
    let carro = JSON.parse(localStorage.getItem('carrito'))
    if (!carro) {
      this.carrito = []
    } else {
      this.carrito = carro
    }
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
  mounted() {

  },
  methods: {
    loadData() {
      axios.get('/api/products')
        .then((response => {
          this.products = response.data
          this.backUp = this.products
          this.supplementsFilter = response.data.filter(product => product.productCategory == "SUPPLEMENTS")
          this.clothFilter = response.data.filter(product => product.productCategory == "CLOTHES")
          this.equipmentFilter = response.data.filter(product => product.productCategory == "EQUIPMENT")
          this.numeroCarrinho()
        }))
    },
    logOut(){
      return Swal.fire({
      title: 'Are You Sure want to log out?',
      text: "You must be sure",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, Log Out!'
    }).then((result) => {
      if (result.isConfirmed) {
          axios.post(`/api/logout`).catch(function (error) {
              let errorData = error.response.data
              Swal.fire({
              icon: 'error',
              title: 'Warning!!!!!',
              text: `${errorData}`,
            })}
            )
        Swal.fire(
          'Logged out successfully'
        ).then(response=> window.location.href = "http://localhost:8080/web/index.html")
      }
    }) 
  },
    filterAll() {
      if (this.selectedFilter == "ALL") {
        return this.loadData(this.products)
      }
      if (this.selectedFilter == "SUPPLEMENTS") {
        return this.products = this.supplementsFilter
      }
      if (this.selectedFilter == "EQUIPMENT") {
        return this.products = this.equipmentFilter
      }
      if (this.selectedFilter == "CLOTHES") {
        return this.products = this.clothFilter
      }
    },

    noStockAllert() {
      this.addCartAllert = false;
      this.noStock = true;
      setTimeout(() => {
        this.noStock = false;
      }, 5000);
    },
    numeroCarrinho() {
      let numero = JSON.parse(localStorage.getItem('productsInCart'))
      this.numeroCarrito = numero.length
      
    },
    
    addCart(product) {
      this.addCartAllert = true;
      this.noStock = false;
      setTimeout(() => {
        this.addCartAllert = false;
      }, 3000);
      if (this.cartProducts.includes(product)) {
        product.quantity++;
        localStorage.setItem(
          "productsInCart",
          JSON.stringify(this.cartProducts)
        );
      } else {
        product.quantity = 1;
        this.cartProducts.push(product);
        localStorage.setItem(
          "productsInCart",
          JSON.stringify(this.cartProducts)
          );
        }
        this.numeroCarrinho() 
      this.subtotal = 0;
      this.cartProducts.forEach((product) => {
        this.subtotal += product.price * product.quantity;
      });
      localStorage.setItem("subtotal", JSON.stringify(this.subtotal))
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Your item has been added to the shopping cart',
        showConfirmButton: false,
        timer: 1500
      })
      console.log(this.cartProducts, this.subtotal);
    },

    lowerPrice() {
      this.products.sort(function (a, b) { return a.price - b.price });
    },
    higherPrice() {
      this.products.sort(function (a, b) { return b.price - a.price });
    },

  },
  computed: {
    filterSearch() {
      let search = this.backUp.filter(backUps => backUps.name.toLowerCase().includes(this.textSearch.toLowerCase()))
      if (this.selectedFilter.length && this.selectedFilter != "ALL") {
        this.products = search.filter(product => this.selectedFilter.includes(product.productCategory))
      }
      else {
        this.products = search
      }
      if (this.selectedFilter == "ALL") {
        this.products
      }

    },

  }
}).mount('#app')
