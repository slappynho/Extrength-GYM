const app = Vue.createApp ({
    data() {
        return {
        }
    },

    created() {

    },

    methods: {
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
            addWorkOut(dato){
              return Swal.fire({
                title: 'Are You Sure?',
                text: "You will add a workout",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, add Workout!'
              }).then((result) => {
                if (result.isConfirmed) {
                    axios.post('/api/workouts',`id=${dato}`).catch(function (error) {
                        let errorData = error.response.data
                        Swal.fire({
                        icon: 'error',
                        title: 'Warning!!!!!',
                        text: `${errorData}`,
                      })}
                      )
                  Swal.fire(
                    'Workout added successfully'
                  ).then(response=> window.location.href = "http://localhost:8080/web/indexC.html")
                }
              }) 
            }

            
        
        },
    }).mount('#app')
    
    
