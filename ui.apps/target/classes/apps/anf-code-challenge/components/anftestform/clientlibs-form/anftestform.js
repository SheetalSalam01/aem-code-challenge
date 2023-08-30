// ***Begin Code - Sheetal Salam*** 
const anfTestForm = document.getElementById("anf-test-form");
if (anfTestForm) {
    document.getElementById("new_form").addEventListener("submit", async (event) => {
      event.preventDefault();
  
      try {
        // Get country from JSON response
        const currenturl = document.location.pathname.replace(/\.[^.\/?&]+$/, "");
        const countryNodePath =
          currenturl + "/jcr:content/root/container/container/anftestform/country";
        const countryResponse = await fetch(countryNodePath + ".json");
        let countryData = await countryResponse.json();
        let country = countryData.countries;
  
        let userData = JSON.stringify({
          firstname: new_form.firstname.value,
          lastname: new_form.lastname.value,
          age: Number(new_form.age.value),
          country: country,
        });
        let response = await fetch("/bin/saveUserDetails", {
          method: "POST",
          body: userData,
          headers: {
            "Content-type": "application/json; charset=UTF-8",
          },
        });
        let output = await response.json();
    
        if(output.errorMessage ==="You are not eligible"){
            document.getElementsByClassName("anf-form-message")[0].style.display="block";
            setTimeout(() => {
              location.reload();
            }, 2000);
          }
          else{
            alert("Form Saved Successfully");
            location.reload();

        }
      } catch (error) {
        console.error("Error:", error);
      }
    });
  
}
// ***END Code*****