import { MockLocation } from '@jewel998&#x2F;mock-location';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    MockLocation.echo({ value: inputValue })
}
