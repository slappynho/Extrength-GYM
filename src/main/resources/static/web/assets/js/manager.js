const { createApp } = Vue

createApp({
    data() {
        return {
            products: [],
            categories: ["Supplements", "Equipment", "Clothes"],
            searchFilterInput: "",
            moneyFormat: new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }),
            name: "",
            category: "",
            price: 0.00,
            stock: 0,
            description: "",
            file: "",
            ideaso: 0
        }
    },
    created() {
        this.loadData()
    },
    methods: {
        onChangeFile(event) {
            this.file = event.target.files || event.dataTransfer.files
        },
        addNewProduct() {
            return Swal.fire({
                title: 'Are you  Sure?',
                text: "You will add a product",
                icon: 'warning',
                background: '#3f3f3f',
                color: '#edc93b',
                showCancelButton: true,
                confirmButtonColor: '#edc93b',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, disable!'
              }).then((result) => {
                if (result.isConfirmed) {
                    axios.post("/api/products", {
                        "name" :  this.name, 
                        "description" : this.description, 
                        "category" : this.category , 
                        "price" : this.price, 
                        "stock" : this.stock, 
                        "files" : this.file[0] 
                    }, 
                        { 
                            headers: {'Content-Type': 'multipart/form-data'} 
                        }).catch(function (error) {
                        let errorData = error.response.data
                        Swal.fire({
                        icon: 'error',
                        title: 'Warining!!!!!',
                        text: `${errorData}`,
                        background: '#3f3f3f',
                        color: '#edc93b',
                      })}
                      )
                  Swal.fire(

                    'Product add in the basedate',
                    'Well Done.',
                    'success'
                    
                  ).then(response=> window.location.href = "http://localhost:8080/web/manager.html")

                }
              }) 

        },
        
/*           addProduct(){
                axios.post("/api/products",`name=${this.name}&description=${this.description}&category=${this.category}&price=${this.price}&stock${this.stock}`,{
                    "files": this.file
                }, { headers: {'Content-Type': 'multipart/form-data'} }).then(response => {
                    console.log(response)
                    alert("listo capo producto agregado", response)
                }) 
        },  */
        
        loadData() {
            axios.get("/api/products")
                .then(response => {
                    this.products = response.data
                    console.log(this.products)
                    this.filteredProducts = this.products
                }).catch(error => error)
        },
        eraseProduct(){
            return Swal.fire({
                title: 'Are you  Sure?',
                text: "You will disable a product",
                icon: 'warning',
                background: '#3f3f3f',
                color: '#edc93b',
                showCancelButton: true,
                confirmButtonColor: '#edc93b',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, disable!'
              }).then((result) => {
                if (result.isConfirmed) {
                    axios.patch("/api/products",`id=${this.ideaso}`).catch(function (error) {
                        let errorData = error.response.data
                        Swal.fire({
                        icon: 'error',
                        title: 'Warining!!!!!',
                        text: `${errorData}`,
                        background: '#3f3f3f',
                        color: '#edc93b',
                      })}
                      )
                  Swal.fire(

                    'Product disabled in the basedate',
                    'Well Done.',
                    'success'
                    
                  ).then(response=> window.location.href = "http://localhost:8080/web/manager.html")

                }
              }) 
            
            
        },
        selectFilter(category) {
            this.checkedCategory = category
            if (category == "Suplements") {
                this.subcategories = this.suplements
                this.filteredProducts = this.products.filter(product => product.productCategory == "SUPPLEMENTS")
            } else if (category == "Clothes") {
                this.subcategories = this.clothes
                this.filteredProducts = this.products.filter(product => product.productCategory == "CLOTHES")
            } else if (category == "Equipment") {
                this.subcategories = []
                this.filteredProducts = this.products.filter(product => product.productCategory == "EQUIPMENT")
            }
        },
        searchFilter() {
            this.filteredProducts = this.products.filter(product => product.name.toLowerCase().includes(this.searchFilterInput))
            console.log(this.filteredProducts)
            if (this.searchFilterInput == "") {
                this.filteredProducts = this.products
            }
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
    },
}).mount('#app')