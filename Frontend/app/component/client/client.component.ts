import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../../model/User';
import { UserService } from '../../service/User.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DeviceService } from '../../service/Device.service';
import { Device } from '../../model/Device';
import { ChartService } from '../../service/ChartService';
import Chart from 'chart.js/auto';
import {interval} from "rxjs";
import { WebSocketService } from "../../service/WebSocketService"
import { Message } from "../../model/Message";

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css'],
})
export class ClientComponent implements OnInit {
  message = ' ';
  ownerList: any;
  updateForm: FormGroup | undefined;
  user: User = new User();
  userInfo: String = this.userService.userInfo;
  devicesList: Device[] = [];

  chatMessageForm: FormGroup = this.formBuilder.group({
    message: [null, Validators.required],
  });

  chatMessages: Message[] = [];

  constructor(
    private userService: UserService,
    private deviceService: DeviceService,
    private chartService: ChartService,
    private formBuilder: FormBuilder,
    private router: Router,
    private webSocketService: WebSocketService
  ) {}

  ngOnInit(): void {
    /*
    // Inițializare formular pentru mesaje
    this.chatMessageForm = this.formBuilder.group({
      message: [null, Validators.required],
    });

    // Inițializare conexiune WebSocket pentru mesaje private
    this.webSocketService.initializeConnectionChat(this.handleChatMessage.bind(this));
*/
  }
/*
  sendChatMessage(): void {
    if (this.chatMessageForm?.valid) {
      const messageText = this.chatMessageForm.get('message')?.value;
      const adminId = 49;

      const chatMessage: Message = {
        senderId: parseInt(localStorage.getItem("idUser") || '0', 10),
        receiverId: adminId,
        message: messageText,
      };

      // Trimite mesajul către server
      this.webSocketService.sendMessage(chatMessage);

      // Adaugă mesajul în lista locală pentru afișare instantanee
      this.chatMessages.push(chatMessage);

      // Resetare formular
      this.chatMessageForm.reset();
    }
  }

  private handleChatMessage(message: Message): void {
    // Logica pentru gestionarea mesajelor primite de la server (mesaje private)
    console.log('Received chat message:', message);
    // Adaugă mesajul în lista locală pentru afișare
    this.chatMessages.push(message);
    // Implementează afișarea mesajelor în interfața de utilizator sau alte acțiuni necesare
  }


 */

  saveSelectedDate(event: Event) {
    const selectedDate = (event.target as HTMLInputElement).value;

    console.log('Selected Date:', selectedDate);

    const dateObject = new Date(selectedDate);
    const timestamp = dateObject.getTime();

    localStorage.setItem('selectedDate', timestamp.toString());
    console.log('Timestamp', timestamp);

    interval(10000).subscribe(() => {
      this.function();
    });
  }

  function() {
    // Retrieve idClient and timestamp as strings from localStorage
    const idClientStr = localStorage.getItem('idUser');
    const timestampStr = localStorage.getItem('selectedDate');

    // Convert idClient and timestamp to numbers
    const idClient = idClientStr ? parseInt(idClientStr, 10) : 0;
    const timestamp = timestampStr ? parseInt(timestampStr, 10) : 0;

    // Destroy existing chart if it exists
    const existingChart = this.chartService.getExistingChart();
    if (existingChart) {
      existingChart.destroy();
    }

    this.chartService.getChartByClient(idClient, timestamp).subscribe(
      (chartResponse: any) => {
        const hours = Object.keys(chartResponse.consumptionPerHour);
        const values = Object.values(chartResponse.consumptionPerHour);
        console.log('hours', hours);
        console.log('values', values);
        const ctx = document.getElementById('myChart') as HTMLCanvasElement;
        const newChart = new Chart(ctx, {
          type: 'line',
          data: {
            labels: hours,
            datasets: [
              {
                label: 'Consumption per Hour',
                data: values,
                backgroundColor: 'rgba(0, 0, 192, 1)',
                borderColor: 'rgba(0, 0, 192, 1)',
                borderWidth: 1,
              },
            ],
          },
          options: {
            scales: {
              y: {
                beginAtZero: true,
              },
            },
          },
        });

        // Set the new chart as the existing chart
        this.chartService.setExistingChart(newChart);
      },
      (_error) => {}
    );
  }

  initOwnerDevicesForm() {
    this.updateForm = this.formBuilder.group({
      ownerInput: [null, Validators.required],
      deviceInput: [null, Validators.required],
    });
  }

  ShowDevices() {
    var idUser = localStorage.getItem('idUser');

    this.deviceService.showDevices(idUser).subscribe(
      (res) => {
        this.devicesList = res;
      },
      (_error) => {}
    );

    this.initOwnerDevicesForm();
  }
}
