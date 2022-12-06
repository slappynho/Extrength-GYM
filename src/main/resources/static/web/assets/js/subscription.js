const app = Vue.createApp ({
    data() {
        return {
            subSoli:"",
        }
    },

    created() {

    },

    methods: {
        ComprarSub(){
             return Swal.fire({
                title: 'Are you Sure?',
                text: "You will have to pay in the future",
                icon: 'warning',
                background: '#3f3f3f',
                color: '#edc93b',
                showCancelButton: true,
                confirmButtonColor: '#edc93b',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, pay the sub!'
              }).then((result) => {
                if (result.isConfirmed) {
                    axios.post(`/api/subscriptions`,`id=${this.subSoli}&paymentAuthorization=TRUE`).catch(function (error) {
                        let errorData = error.response.data
                        Swal.fire({
                        icon: 'error',
                        title: 'Warning!!!!!',
                        text: `${errorData}`,
                        background: '#3f3f3f',
                        color: '#edc93b',
                      })}
                      )
                  Swal.fire(

                    'Subscription added',
                    'Please come to our gym to pay the bill.',
                    'success'
                    
                  ).then(response=> window.location.href = "http://localhost:8080/web/indexC.html")

                }
              }) 
            

            
	
            },

            
        
        },
    }).mount('#app')
  