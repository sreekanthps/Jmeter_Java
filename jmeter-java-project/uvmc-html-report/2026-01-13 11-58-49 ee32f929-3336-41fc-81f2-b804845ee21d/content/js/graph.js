/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
$(document).ready(function() {

    $(".click-title").mouseenter( function(    e){
        e.preventDefault();
        this.style.cursor="pointer";
    });
    $(".click-title").mousedown( function(event){
        event.preventDefault();
    });

    // Ugly code while this script is shared among several pages
    try{
        refreshHitsPerSecond(true);
    } catch(e){}
    try{
        refreshResponseTimeOverTime(true);
    } catch(e){}
    try{
        refreshResponseTimePercentiles();
    } catch(e){}
});


var responseTimePercentilesInfos = {
        data: {"result": {"minY": 36.0, "minX": 0.0, "maxY": 7002.0, "series": [{"data": [[0.0, 36.0], [0.1, 36.0], [0.2, 36.0], [0.3, 39.0], [0.4, 39.0], [0.5, 40.0], [0.6, 40.0], [0.7, 40.0], [0.8, 40.0], [0.9, 41.0], [1.0, 41.0], [1.1, 41.0], [1.2, 42.0], [1.3, 42.0], [1.4, 43.0], [1.5, 43.0], [1.6, 43.0], [1.7, 43.0], [1.8, 43.0], [1.9, 43.0], [2.0, 44.0], [2.1, 44.0], [2.2, 44.0], [2.3, 44.0], [2.4, 44.0], [2.5, 44.0], [2.6, 44.0], [2.7, 44.0], [2.8, 44.0], [2.9, 44.0], [3.0, 44.0], [3.1, 45.0], [3.2, 45.0], [3.3, 45.0], [3.4, 45.0], [3.5, 45.0], [3.6, 45.0], [3.7, 45.0], [3.8, 45.0], [3.9, 45.0], [4.0, 45.0], [4.1, 45.0], [4.2, 45.0], [4.3, 45.0], [4.4, 45.0], [4.5, 45.0], [4.6, 45.0], [4.7, 45.0], [4.8, 45.0], [4.9, 46.0], [5.0, 46.0], [5.1, 46.0], [5.2, 46.0], [5.3, 46.0], [5.4, 46.0], [5.5, 46.0], [5.6, 46.0], [5.7, 46.0], [5.8, 46.0], [5.9, 46.0], [6.0, 46.0], [6.1, 46.0], [6.2, 46.0], [6.3, 46.0], [6.4, 46.0], [6.5, 46.0], [6.6, 46.0], [6.7, 46.0], [6.8, 46.0], [6.9, 46.0], [7.0, 46.0], [7.1, 46.0], [7.2, 46.0], [7.3, 46.0], [7.4, 46.0], [7.5, 46.0], [7.6, 46.0], [7.7, 46.0], [7.8, 46.0], [7.9, 46.0], [8.0, 47.0], [8.1, 47.0], [8.2, 47.0], [8.3, 47.0], [8.4, 47.0], [8.5, 47.0], [8.6, 47.0], [8.7, 47.0], [8.8, 47.0], [8.9, 47.0], [9.0, 47.0], [9.1, 47.0], [9.2, 47.0], [9.3, 47.0], [9.4, 47.0], [9.5, 47.0], [9.6, 47.0], [9.7, 47.0], [9.8, 47.0], [9.9, 47.0], [10.0, 47.0], [10.1, 47.0], [10.2, 47.0], [10.3, 47.0], [10.4, 47.0], [10.5, 47.0], [10.6, 48.0], [10.7, 48.0], [10.8, 48.0], [10.9, 48.0], [11.0, 48.0], [11.1, 48.0], [11.2, 48.0], [11.3, 48.0], [11.4, 48.0], [11.5, 48.0], [11.6, 48.0], [11.7, 48.0], [11.8, 48.0], [11.9, 48.0], [12.0, 48.0], [12.1, 48.0], [12.2, 48.0], [12.3, 48.0], [12.4, 48.0], [12.5, 48.0], [12.6, 48.0], [12.7, 48.0], [12.8, 48.0], [12.9, 49.0], [13.0, 49.0], [13.1, 49.0], [13.2, 49.0], [13.3, 49.0], [13.4, 49.0], [13.5, 49.0], [13.6, 49.0], [13.7, 49.0], [13.8, 49.0], [13.9, 49.0], [14.0, 49.0], [14.1, 49.0], [14.2, 49.0], [14.3, 49.0], [14.4, 49.0], [14.5, 49.0], [14.6, 49.0], [14.7, 49.0], [14.8, 49.0], [14.9, 49.0], [15.0, 49.0], [15.1, 49.0], [15.2, 49.0], [15.3, 50.0], [15.4, 50.0], [15.5, 50.0], [15.6, 50.0], [15.7, 50.0], [15.8, 50.0], [15.9, 50.0], [16.0, 50.0], [16.1, 50.0], [16.2, 50.0], [16.3, 50.0], [16.4, 50.0], [16.5, 50.0], [16.6, 50.0], [16.7, 50.0], [16.8, 50.0], [16.9, 50.0], [17.0, 50.0], [17.1, 50.0], [17.2, 50.0], [17.3, 51.0], [17.4, 51.0], [17.5, 51.0], [17.6, 51.0], [17.7, 51.0], [17.8, 51.0], [17.9, 51.0], [18.0, 51.0], [18.1, 51.0], [18.2, 51.0], [18.3, 51.0], [18.4, 51.0], [18.5, 51.0], [18.6, 51.0], [18.7, 51.0], [18.8, 51.0], [18.9, 51.0], [19.0, 51.0], [19.1, 51.0], [19.2, 51.0], [19.3, 51.0], [19.4, 51.0], [19.5, 52.0], [19.6, 52.0], [19.7, 52.0], [19.8, 52.0], [19.9, 52.0], [20.0, 52.0], [20.1, 52.0], [20.2, 52.0], [20.3, 52.0], [20.4, 52.0], [20.5, 52.0], [20.6, 52.0], [20.7, 52.0], [20.8, 52.0], [20.9, 52.0], [21.0, 52.0], [21.1, 52.0], [21.2, 52.0], [21.3, 52.0], [21.4, 52.0], [21.5, 52.0], [21.6, 52.0], [21.7, 52.0], [21.8, 52.0], [21.9, 52.0], [22.0, 52.0], [22.1, 52.0], [22.2, 52.0], [22.3, 53.0], [22.4, 53.0], [22.5, 53.0], [22.6, 53.0], [22.7, 53.0], [22.8, 53.0], [22.9, 53.0], [23.0, 53.0], [23.1, 53.0], [23.2, 53.0], [23.3, 53.0], [23.4, 53.0], [23.5, 53.0], [23.6, 53.0], [23.7, 53.0], [23.8, 53.0], [23.9, 53.0], [24.0, 53.0], [24.1, 53.0], [24.2, 53.0], [24.3, 54.0], [24.4, 54.0], [24.5, 54.0], [24.6, 54.0], [24.7, 54.0], [24.8, 54.0], [24.9, 54.0], [25.0, 54.0], [25.1, 54.0], [25.2, 54.0], [25.3, 54.0], [25.4, 54.0], [25.5, 54.0], [25.6, 54.0], [25.7, 54.0], [25.8, 54.0], [25.9, 54.0], [26.0, 54.0], [26.1, 54.0], [26.2, 54.0], [26.3, 55.0], [26.4, 55.0], [26.5, 55.0], [26.6, 55.0], [26.7, 55.0], [26.8, 55.0], [26.9, 55.0], [27.0, 55.0], [27.1, 55.0], [27.2, 55.0], [27.3, 55.0], [27.4, 55.0], [27.5, 55.0], [27.6, 56.0], [27.7, 56.0], [27.8, 56.0], [27.9, 56.0], [28.0, 56.0], [28.1, 57.0], [28.2, 57.0], [28.3, 57.0], [28.4, 57.0], [28.5, 58.0], [28.6, 58.0], [28.7, 58.0], [28.8, 58.0], [28.9, 58.0], [29.0, 59.0], [29.1, 59.0], [29.2, 59.0], [29.3, 59.0], [29.4, 59.0], [29.5, 59.0], [29.6, 61.0], [29.7, 61.0], [29.8, 61.0], [29.9, 61.0], [30.0, 61.0], [30.1, 61.0], [30.2, 61.0], [30.3, 61.0], [30.4, 61.0], [30.5, 62.0], [30.6, 62.0], [30.7, 62.0], [30.8, 62.0], [30.9, 62.0], [31.0, 62.0], [31.1, 62.0], [31.2, 62.0], [31.3, 62.0], [31.4, 62.0], [31.5, 62.0], [31.6, 62.0], [31.7, 62.0], [31.8, 63.0], [31.9, 63.0], [32.0, 63.0], [32.1, 64.0], [32.2, 64.0], [32.3, 64.0], [32.4, 64.0], [32.5, 64.0], [32.6, 64.0], [32.7, 65.0], [32.8, 65.0], [32.9, 65.0], [33.0, 65.0], [33.1, 65.0], [33.2, 65.0], [33.3, 65.0], [33.4, 65.0], [33.5, 65.0], [33.6, 66.0], [33.7, 66.0], [33.8, 67.0], [33.9, 67.0], [34.0, 67.0], [34.1, 67.0], [34.2, 67.0], [34.3, 67.0], [34.4, 67.0], [34.5, 67.0], [34.6, 67.0], [34.7, 67.0], [34.8, 67.0], [34.9, 68.0], [35.0, 68.0], [35.1, 68.0], [35.2, 68.0], [35.3, 68.0], [35.4, 68.0], [35.5, 68.0], [35.6, 68.0], [35.7, 68.0], [35.8, 68.0], [35.9, 68.0], [36.0, 68.0], [36.1, 68.0], [36.2, 68.0], [36.3, 69.0], [36.4, 69.0], [36.5, 69.0], [36.6, 69.0], [36.7, 69.0], [36.8, 69.0], [36.9, 69.0], [37.0, 69.0], [37.1, 70.0], [37.2, 70.0], [37.3, 70.0], [37.4, 70.0], [37.5, 70.0], [37.6, 70.0], [37.7, 70.0], [37.8, 70.0], [37.9, 70.0], [38.0, 71.0], [38.1, 71.0], [38.2, 71.0], [38.3, 71.0], [38.4, 71.0], [38.5, 71.0], [38.6, 71.0], [38.7, 71.0], [38.8, 71.0], [38.9, 71.0], [39.0, 71.0], [39.1, 72.0], [39.2, 72.0], [39.3, 72.0], [39.4, 72.0], [39.5, 72.0], [39.6, 72.0], [39.7, 72.0], [39.8, 72.0], [39.9, 72.0], [40.0, 72.0], [40.1, 72.0], [40.2, 72.0], [40.3, 72.0], [40.4, 72.0], [40.5, 72.0], [40.6, 72.0], [40.7, 73.0], [40.8, 73.0], [40.9, 73.0], [41.0, 73.0], [41.1, 73.0], [41.2, 73.0], [41.3, 73.0], [41.4, 73.0], [41.5, 73.0], [41.6, 73.0], [41.7, 73.0], [41.8, 74.0], [41.9, 74.0], [42.0, 74.0], [42.1, 74.0], [42.2, 74.0], [42.3, 74.0], [42.4, 74.0], [42.5, 74.0], [42.6, 74.0], [42.7, 75.0], [42.8, 75.0], [42.9, 75.0], [43.0, 75.0], [43.1, 75.0], [43.2, 75.0], [43.3, 75.0], [43.4, 75.0], [43.5, 75.0], [43.6, 75.0], [43.7, 75.0], [43.8, 75.0], [43.9, 75.0], [44.0, 75.0], [44.1, 75.0], [44.2, 75.0], [44.3, 75.0], [44.4, 76.0], [44.5, 76.0], [44.6, 76.0], [44.7, 76.0], [44.8, 76.0], [44.9, 76.0], [45.0, 76.0], [45.1, 76.0], [45.2, 76.0], [45.3, 76.0], [45.4, 76.0], [45.5, 76.0], [45.6, 76.0], [45.7, 76.0], [45.8, 76.0], [45.9, 76.0], [46.0, 76.0], [46.1, 76.0], [46.2, 76.0], [46.3, 76.0], [46.4, 77.0], [46.5, 77.0], [46.6, 77.0], [46.7, 77.0], [46.8, 77.0], [46.9, 77.0], [47.0, 77.0], [47.1, 77.0], [47.2, 77.0], [47.3, 77.0], [47.4, 77.0], [47.5, 77.0], [47.6, 77.0], [47.7, 77.0], [47.8, 77.0], [47.9, 77.0], [48.0, 77.0], [48.1, 77.0], [48.2, 79.0], [48.3, 79.0], [48.4, 79.0], [48.5, 79.0], [48.6, 79.0], [48.7, 79.0], [48.8, 79.0], [48.9, 79.0], [49.0, 79.0], [49.1, 79.0], [49.2, 79.0], [49.3, 79.0], [49.4, 79.0], [49.5, 79.0], [49.6, 79.0], [49.7, 79.0], [49.8, 79.0], [49.9, 80.0], [50.0, 80.0], [50.1, 80.0], [50.2, 80.0], [50.3, 80.0], [50.4, 80.0], [50.5, 80.0], [50.6, 81.0], [50.7, 81.0], [50.8, 81.0], [50.9, 81.0], [51.0, 81.0], [51.1, 81.0], [51.2, 81.0], [51.3, 81.0], [51.4, 81.0], [51.5, 81.0], [51.6, 81.0], [51.7, 81.0], [51.8, 81.0], [51.9, 81.0], [52.0, 81.0], [52.1, 81.0], [52.2, 81.0], [52.3, 81.0], [52.4, 81.0], [52.5, 81.0], [52.6, 81.0], [52.7, 81.0], [52.8, 81.0], [52.9, 81.0], [53.0, 81.0], [53.1, 81.0], [53.2, 81.0], [53.3, 81.0], [53.4, 81.0], [53.5, 82.0], [53.6, 82.0], [53.7, 82.0], [53.8, 82.0], [53.9, 82.0], [54.0, 82.0], [54.1, 83.0], [54.2, 83.0], [54.3, 83.0], [54.4, 83.0], [54.5, 83.0], [54.6, 83.0], [54.7, 83.0], [54.8, 83.0], [54.9, 83.0], [55.0, 83.0], [55.1, 83.0], [55.2, 84.0], [55.3, 84.0], [55.4, 84.0], [55.5, 84.0], [55.6, 84.0], [55.7, 85.0], [55.8, 85.0], [55.9, 86.0], [56.0, 86.0], [56.1, 86.0], [56.2, 86.0], [56.3, 87.0], [56.4, 87.0], [56.5, 87.0], [56.6, 87.0], [56.7, 87.0], [56.8, 87.0], [56.9, 87.0], [57.0, 87.0], [57.1, 87.0], [57.2, 89.0], [57.3, 89.0], [57.4, 89.0], [57.5, 89.0], [57.6, 89.0], [57.7, 89.0], [57.8, 89.0], [57.9, 90.0], [58.0, 90.0], [58.1, 91.0], [58.2, 91.0], [58.3, 91.0], [58.4, 91.0], [58.5, 92.0], [58.6, 92.0], [58.7, 92.0], [58.8, 92.0], [58.9, 92.0], [59.0, 92.0], [59.1, 92.0], [59.2, 92.0], [59.3, 92.0], [59.4, 92.0], [59.5, 92.0], [59.6, 92.0], [59.7, 93.0], [59.8, 93.0], [59.9, 93.0], [60.0, 93.0], [60.1, 93.0], [60.2, 93.0], [60.3, 94.0], [60.4, 94.0], [60.5, 95.0], [60.6, 95.0], [60.7, 95.0], [60.8, 95.0], [60.9, 95.0], [61.0, 95.0], [61.1, 95.0], [61.2, 96.0], [61.3, 96.0], [61.4, 96.0], [61.5, 96.0], [61.6, 96.0], [61.7, 96.0], [61.8, 96.0], [61.9, 97.0], [62.0, 97.0], [62.1, 97.0], [62.2, 97.0], [62.3, 97.0], [62.4, 97.0], [62.5, 98.0], [62.6, 98.0], [62.7, 98.0], [62.8, 98.0], [62.9, 98.0], [63.0, 99.0], [63.1, 99.0], [63.2, 101.0], [63.3, 101.0], [63.4, 103.0], [63.5, 103.0], [63.6, 103.0], [63.7, 103.0], [63.8, 105.0], [63.9, 105.0], [64.0, 105.0], [64.1, 106.0], [64.2, 106.0], [64.3, 107.0], [64.4, 107.0], [64.5, 108.0], [64.6, 108.0], [64.7, 110.0], [64.8, 110.0], [64.9, 110.0], [65.0, 126.0], [65.1, 126.0], [65.2, 127.0], [65.3, 127.0], [65.4, 130.0], [65.5, 130.0], [65.6, 132.0], [65.7, 132.0], [65.8, 133.0], [65.9, 133.0], [66.0, 133.0], [66.1, 134.0], [66.2, 134.0], [66.3, 134.0], [66.4, 134.0], [66.5, 135.0], [66.6, 135.0], [66.7, 139.0], [66.8, 139.0], [66.9, 139.0], [67.0, 139.0], [67.1, 139.0], [67.2, 140.0], [67.3, 140.0], [67.4, 141.0], [67.5, 141.0], [67.6, 141.0], [67.7, 141.0], [67.8, 142.0], [67.9, 142.0], [68.0, 144.0], [68.1, 144.0], [68.2, 144.0], [68.3, 144.0], [68.4, 144.0], [68.5, 147.0], [68.6, 147.0], [68.7, 149.0], [68.8, 149.0], [68.9, 149.0], [69.0, 149.0], [69.1, 150.0], [69.2, 150.0], [69.3, 150.0], [69.4, 150.0], [69.5, 150.0], [69.6, 151.0], [69.7, 151.0], [69.8, 152.0], [69.9, 152.0], [70.0, 153.0], [70.1, 153.0], [70.2, 155.0], [70.3, 155.0], [70.4, 155.0], [70.5, 155.0], [70.6, 155.0], [70.7, 157.0], [70.8, 157.0], [70.9, 162.0], [71.0, 162.0], [71.1, 162.0], [71.2, 162.0], [71.3, 162.0], [71.4, 163.0], [71.5, 163.0], [71.6, 164.0], [71.7, 164.0], [71.8, 164.0], [71.9, 164.0], [72.0, 164.0], [72.1, 164.0], [72.2, 164.0], [72.3, 164.0], [72.4, 164.0], [72.5, 165.0], [72.6, 165.0], [72.7, 167.0], [72.8, 167.0], [72.9, 168.0], [73.0, 168.0], [73.1, 168.0], [73.2, 168.0], [73.3, 168.0], [73.4, 168.0], [73.5, 168.0], [73.6, 170.0], [73.7, 170.0], [73.8, 171.0], [73.9, 171.0], [74.0, 171.0], [74.1, 171.0], [74.2, 171.0], [74.3, 171.0], [74.4, 171.0], [74.5, 171.0], [74.6, 171.0], [74.7, 172.0], [74.8, 172.0], [74.9, 173.0], [75.0, 173.0], [75.1, 173.0], [75.2, 173.0], [75.3, 173.0], [75.4, 173.0], [75.5, 173.0], [75.6, 173.0], [75.7, 173.0], [75.8, 173.0], [75.9, 173.0], [76.0, 174.0], [76.1, 174.0], [76.2, 174.0], [76.3, 174.0], [76.4, 177.0], [76.5, 177.0], [76.6, 177.0], [76.7, 177.0], [76.8, 177.0], [76.9, 178.0], [77.0, 178.0], [77.1, 179.0], [77.2, 179.0], [77.3, 179.0], [77.4, 179.0], [77.5, 180.0], [77.6, 180.0], [77.7, 180.0], [77.8, 182.0], [77.9, 182.0], [78.0, 183.0], [78.1, 183.0], [78.2, 184.0], [78.3, 184.0], [78.4, 184.0], [78.5, 184.0], [78.6, 184.0], [78.7, 184.0], [78.8, 184.0], [78.9, 185.0], [79.0, 185.0], [79.1, 185.0], [79.2, 185.0], [79.3, 185.0], [79.4, 185.0], [79.5, 187.0], [79.6, 187.0], [79.7, 187.0], [79.8, 187.0], [79.9, 187.0], [80.0, 187.0], [80.1, 187.0], [80.2, 189.0], [80.3, 189.0], [80.4, 189.0], [80.5, 189.0], [80.6, 189.0], [80.7, 189.0], [80.8, 192.0], [80.9, 192.0], [81.0, 192.0], [81.1, 192.0], [81.2, 192.0], [81.3, 192.0], [81.4, 192.0], [81.5, 193.0], [81.6, 193.0], [81.7, 193.0], [81.8, 193.0], [81.9, 194.0], [82.0, 194.0], [82.1, 194.0], [82.2, 195.0], [82.3, 195.0], [82.4, 195.0], [82.5, 195.0], [82.6, 196.0], [82.7, 196.0], [82.8, 197.0], [82.9, 197.0], [83.0, 197.0], [83.1, 198.0], [83.2, 198.0], [83.3, 198.0], [83.4, 198.0], [83.5, 200.0], [83.6, 200.0], [83.7, 201.0], [83.8, 201.0], [83.9, 202.0], [84.0, 202.0], [84.1, 202.0], [84.2, 202.0], [84.3, 202.0], [84.4, 202.0], [84.5, 202.0], [84.6, 202.0], [84.7, 202.0], [84.8, 203.0], [84.9, 203.0], [85.0, 204.0], [85.1, 204.0], [85.2, 204.0], [85.3, 205.0], [85.4, 205.0], [85.5, 206.0], [85.6, 206.0], [85.7, 206.0], [85.8, 206.0], [85.9, 206.0], [86.0, 206.0], [86.1, 207.0], [86.2, 207.0], [86.3, 207.0], [86.4, 207.0], [86.5, 207.0], [86.6, 207.0], [86.7, 207.0], [86.8, 208.0], [86.9, 208.0], [87.0, 209.0], [87.1, 209.0], [87.2, 210.0], [87.3, 210.0], [87.4, 210.0], [87.5, 210.0], [87.6, 210.0], [87.7, 213.0], [87.8, 213.0], [87.9, 214.0], [88.0, 214.0], [88.1, 215.0], [88.2, 215.0], [88.3, 215.0], [88.4, 217.0], [88.5, 217.0], [88.6, 217.0], [88.7, 217.0], [88.8, 217.0], [88.9, 217.0], [89.0, 217.0], [89.1, 217.0], [89.2, 218.0], [89.3, 218.0], [89.4, 218.0], [89.5, 219.0], [89.6, 219.0], [89.7, 219.0], [89.8, 219.0], [89.9, 220.0], [90.0, 220.0], [90.1, 221.0], [90.2, 221.0], [90.3, 221.0], [90.4, 221.0], [90.5, 221.0], [90.6, 222.0], [90.7, 222.0], [90.8, 222.0], [90.9, 222.0], [91.0, 224.0], [91.1, 224.0], [91.2, 224.0], [91.3, 224.0], [91.4, 225.0], [91.5, 225.0], [91.6, 225.0], [91.7, 228.0], [91.8, 228.0], [91.9, 228.0], [92.0, 228.0], [92.1, 229.0], [92.2, 229.0], [92.3, 229.0], [92.4, 229.0], [92.5, 230.0], [92.6, 230.0], [92.7, 230.0], [92.8, 230.0], [92.9, 230.0], [93.0, 231.0], [93.1, 231.0], [93.2, 232.0], [93.3, 232.0], [93.4, 232.0], [93.5, 232.0], [93.6, 235.0], [93.7, 235.0], [93.8, 235.0], [93.9, 238.0], [94.0, 238.0], [94.1, 240.0], [94.2, 240.0], [94.3, 241.0], [94.4, 241.0], [94.5, 247.0], [94.6, 247.0], [94.7, 247.0], [94.8, 248.0], [94.9, 248.0], [95.0, 250.0], [95.1, 250.0], [95.2, 251.0], [95.3, 251.0], [95.4, 257.0], [95.5, 257.0], [95.6, 258.0], [95.7, 258.0], [95.8, 258.0], [95.9, 261.0], [96.0, 261.0], [96.1, 261.0], [96.2, 261.0], [96.3, 267.0], [96.4, 267.0], [96.5, 268.0], [96.6, 268.0], [96.7, 275.0], [96.8, 275.0], [96.9, 275.0], [97.0, 276.0], [97.1, 276.0], [97.2, 280.0], [97.3, 280.0], [97.4, 290.0], [97.5, 290.0], [97.6, 313.0], [97.7, 313.0], [97.8, 336.0], [97.9, 336.0], [98.0, 336.0], [98.1, 352.0], [98.2, 352.0], [98.3, 371.0], [98.4, 371.0], [98.5, 395.0], [98.6, 395.0], [98.7, 404.0], [98.8, 404.0], [98.9, 437.0], [99.0, 437.0], [99.1, 437.0], [99.2, 1046.0], [99.3, 1046.0], [99.4, 1072.0], [99.5, 1072.0], [99.6, 1433.0], [99.7, 1433.0], [99.8, 7002.0], [99.9, 7002.0]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
        getOptions: function() {
            return {
                series: {
                    points: { show: false }
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentiles'
                },
                xaxis: {
                    tickDecimals: 1,
                    axisLabel: "Percentiles",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Percentile value in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : %x.2 percentile was %y ms"
                },
                selection: { mode: "xy" },
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentiles"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesPercentiles"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesPercentiles"), dataset, prepareOverviewOptions(options));
        }
};

/**
 * @param elementId Id of element where we display message
 */
function setEmptyGraph(elementId) {
    $(function() {
        $(elementId).text("No graph series with filter="+seriesFilter);
    });
}

// Response times percentiles
function refreshResponseTimePercentiles() {
    var infos = responseTimePercentilesInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimePercentiles");
        return;
    }
    if (isGraph($("#flotResponseTimesPercentiles"))){
        infos.createGraph();
    } else {
        var choiceContainer = $("#choicesResponseTimePercentiles");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesPercentiles", "#overviewResponseTimesPercentiles");
        $('#bodyResponseTimePercentiles .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimeDistributionInfos = {
        data: {"result": {"minY": 1.0, "minX": 0.0, "maxY": 286.0, "series": [{"data": [[0.0, 286.0], [300.0, 5.0], [1400.0, 1.0], [100.0, 92.0], [400.0, 2.0], [200.0, 64.0], [7000.0, 1.0], [1000.0, 2.0]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 7000.0, "title": "Response Time Distribution"}},
        getOptions: function() {
            var granularity = this.data.result.granularity;
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    barWidth: this.data.result.granularity
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " responses for " + label + " were between " + xval + " and " + (xval + granularity) + " ms";
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimeDistribution"), prepareData(data.result.series, $("#choicesResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshResponseTimeDistribution() {
    var infos = responseTimeDistributionInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeDistribution");
        return;
    }
    if (isGraph($("#flotResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var syntheticResponseTimeDistributionInfos = {
        data: {"result": {"minY": 1.0, "minX": 0.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1,500ms"], [2, "Requests having \nresponse time > 1,500ms"], [3, "Requests in error"]], "maxY": 449.0, "series": [{"data": [[0.0, 449.0]], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 3.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1,500ms", "isController": false}, {"data": [[2.0, 1.0]], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1,500ms", "isController": false}, {"data": [], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 2.0, "title": "Synthetic Response Times Distribution"}},
        getOptions: function() {
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendSyntheticResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times ranges",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                    tickLength:0,
                    min:-0.5,
                    max:3.5
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    align: "center",
                    barWidth: 0.25,
                    fill:.75
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " " + label;
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            options.xaxis.ticks = data.result.ticks;
            $.plot($("#flotSyntheticResponseTimeDistribution"), prepareData(data.result.series, $("#choicesSyntheticResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshSyntheticResponseTimeDistribution() {
    var infos = syntheticResponseTimeDistributionInfos;
    prepareSeries(infos.data, true);
    if (isGraph($("#flotSyntheticResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerSyntheticResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var activeThreadsOverTimeInfos = {
        data: {"result": {"minY": 1.0, "minX": 1.76831988E12, "maxY": 1.0, "series": [{"data": [[1.76831994E12, 1.0], [1.76831988E12, 1.0]], "isOverall": false, "label": "Thread Group", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.76831994E12, "title": "Active Threads Over Time"}},
        getOptions: function() {
            return {
                series: {
                    stack: true,
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 6,
                    show: true,
                    container: '#legendActiveThreadsOverTime'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                selection: {
                    mode: 'xy'
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : At %x there were %y active threads"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesActiveThreadsOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotActiveThreadsOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewActiveThreadsOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Active Threads Over Time
function refreshActiveThreadsOverTime(fixTimestamps) {
    var infos = activeThreadsOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotActiveThreadsOverTime"))) {
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesActiveThreadsOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotActiveThreadsOverTime", "#overviewActiveThreadsOverTime");
        $('#footerActiveThreadsOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var timeVsThreadsInfos = {
        data: {"result": {"minY": 134.78587196467987, "minX": 1.0, "maxY": 134.78587196467987, "series": [{"data": [[1.0, 134.78587196467987]], "isOverall": false, "label": "HTTP Request", "isController": false}, {"data": [[1.0, 134.78587196467987]], "isOverall": false, "label": "HTTP Request-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 1.0, "title": "Time VS Threads"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: { noColumns: 2,show: true, container: '#legendTimeVsThreads' },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s: At %x.2 active threads, Average response time was %y.2 ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesTimeVsThreads"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotTimesVsThreads"), dataset, options);
            // setup overview
            $.plot($("#overviewTimesVsThreads"), dataset, prepareOverviewOptions(options));
        }
};

// Time vs threads
function refreshTimeVsThreads(){
    var infos = timeVsThreadsInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTimeVsThreads");
        return;
    }
    if(isGraph($("#flotTimesVsThreads"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTimeVsThreads");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTimesVsThreads", "#overviewTimesVsThreads");
        $('#footerTimeVsThreads .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var bytesThroughputOverTimeInfos = {
        data : {"result": {"minY": 114.26666666666667, "minX": 1.76831988E12, "maxY": 11104.6, "series": [{"data": [[1.76831994E12, 4140.783333333334], [1.76831988E12, 114.26666666666667]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.76831994E12, 11104.6], [1.76831988E12, 286.43333333333334]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.76831994E12, "title": "Bytes Throughput Over Time"}},
        getOptions : function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity) ,
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Bytes / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendBytesThroughputOverTime'
                },
                selection: {
                    mode: "xy"
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y"
                }
            };
        },
        createGraph : function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesBytesThroughputOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotBytesThroughputOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewBytesThroughputOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Bytes throughput Over Time
function refreshBytesThroughputOverTime(fixTimestamps) {
    var infos = bytesThroughputOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotBytesThroughputOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesBytesThroughputOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotBytesThroughputOverTime", "#overviewBytesThroughputOverTime");
        $('#footerBytesThroughputOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimesOverTimeInfos = {
        data: {"result": {"minY": 115.5782312925171, "minX": 1.76831988E12, "maxY": 840.6666666666667, "series": [{"data": [[1.76831994E12, 115.5782312925171], [1.76831988E12, 840.6666666666667]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.76831994E12, "title": "Response Time Over Time"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average response time was %y ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Times Over Time
function refreshResponseTimeOverTime(fixTimestamps) {
    var infos = responseTimesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotResponseTimesOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesOverTime", "#overviewResponseTimesOverTime");
        $('#footerResponseTimesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var latenciesOverTimeInfos = {
        data: {"result": {"minY": 114.02040816326526, "minX": 1.76831988E12, "maxY": 839.4166666666666, "series": [{"data": [[1.76831994E12, 114.02040816326526], [1.76831988E12, 839.4166666666666]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.76831994E12, "title": "Latencies Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response latencies in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendLatenciesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average latency was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesLatenciesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotLatenciesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewLatenciesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Latencies Over Time
function refreshLatenciesOverTime(fixTimestamps) {
    var infos = latenciesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyLatenciesOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotLatenciesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesLatenciesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotLatenciesOverTime", "#overviewLatenciesOverTime");
        $('#footerLatenciesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var connectTimeOverTimeInfos = {
        data: {"result": {"minY": 32.8231292517007, "minX": 1.76831988E12, "maxY": 36.666666666666664, "series": [{"data": [[1.76831994E12, 32.8231292517007], [1.76831988E12, 36.666666666666664]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.76831994E12, "title": "Connect Time Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getConnectTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average Connect Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendConnectTimeOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average connect time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesConnectTimeOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotConnectTimeOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewConnectTimeOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Connect Time Over Time
function refreshConnectTimeOverTime(fixTimestamps) {
    var infos = connectTimeOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyConnectTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotConnectTimeOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesConnectTimeOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotConnectTimeOverTime", "#overviewConnectTimeOverTime");
        $('#footerConnectTimeOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var responseTimePercentilesOverTimeInfos = {
        data: {"result": {"minY": 36.0, "minX": 1.76831988E12, "maxY": 7002.0, "series": [{"data": [[1.76831994E12, 1072.0], [1.76831988E12, 7002.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.76831994E12, 36.0], [1.76831988E12, 54.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.76831994E12, 217.8], [1.76831988E12, 5331.300000000007]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.76831994E12, 384.9199999999996], [1.76831988E12, 7002.0]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.76831994E12, 79.0], [1.76831988E12, 161.5]], "isOverall": false, "label": "Median", "isController": false}, {"data": [[1.76831994E12, 239.79999999999995], [1.76831988E12, 7002.0]], "isOverall": false, "label": "95th percentile", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.76831994E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Response Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentilesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Response time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentilesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimePercentilesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimePercentilesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Time Percentiles Over Time
function refreshResponseTimePercentilesOverTime(fixTimestamps) {
    var infos = responseTimePercentilesOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotResponseTimePercentilesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimePercentilesOverTime", "#overviewResponseTimePercentilesOverTime");
        $('#footerResponseTimePercentilesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var responseTimeVsRequestInfos = {
    data: {"result": {"minY": 70.0, "minX": 1.0, "maxY": 574.0, "series": [{"data": [[1.0, 574.0], [8.0, 93.5], [4.0, 210.0], [9.0, 75.0], [5.0, 186.5], [10.0, 74.5], [11.0, 70.0], [6.0, 178.0], [12.0, 74.5], [3.0, 79.0], [7.0, 132.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 12.0, "title": "Response Time Vs Request"}},
    getOptions: function() {
        return {
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Response Time in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: {
                noColumns: 2,
                show: true,
                container: '#legendResponseTimeVsRequest'
            },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median response time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesResponseTimeVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotResponseTimeVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewResponseTimeVsRequest"), dataset, prepareOverviewOptions(options));

    }
};

// Response Time vs Request
function refreshResponseTimeVsRequest() {
    var infos = responseTimeVsRequestInfos;
    prepareSeries(infos.data);
    if (isGraph($("#flotResponseTimeVsRequest"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeVsRequest");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimeVsRequest", "#overviewResponseTimeVsRequest");
        $('#footerResponseRimeVsRequest .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var latenciesVsRequestInfos = {
    data: {"result": {"minY": 69.0, "minX": 1.0, "maxY": 573.5, "series": [{"data": [[1.0, 573.5], [8.0, 89.0], [4.0, 209.5], [9.0, 74.0], [5.0, 186.5], [10.0, 72.5], [11.0, 69.0], [6.0, 177.5], [12.0, 74.5], [3.0, 79.0], [7.0, 132.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 12.0, "title": "Latencies Vs Request"}},
    getOptions: function() {
        return{
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Latency in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: { noColumns: 2,show: true, container: '#legendLatencyVsRequest' },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median Latency time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesLatencyVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotLatenciesVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewLatenciesVsRequest"), dataset, prepareOverviewOptions(options));
    }
};

// Latencies vs Request
function refreshLatenciesVsRequest() {
        var infos = latenciesVsRequestInfos;
        prepareSeries(infos.data);
        if(isGraph($("#flotLatenciesVsRequest"))){
            infos.createGraph();
        }else{
            var choiceContainer = $("#choicesLatencyVsRequest");
            createLegend(choiceContainer, infos);
            infos.createGraph();
            setGraphZoomable("#flotLatenciesVsRequest", "#overviewLatenciesVsRequest");
            $('#footerLatenciesVsRequest .legendColorBox > div').each(function(i){
                $(this).clone().prependTo(choiceContainer.find("li").eq(i));
            });
        }
};

var hitsPerSecondInfos = {
        data: {"result": {"minY": 0.21666666666666667, "minX": 1.76831988E12, "maxY": 7.333333333333333, "series": [{"data": [[1.76831994E12, 7.333333333333333], [1.76831988E12, 0.21666666666666667]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.76831994E12, "title": "Hits Per Second"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of hits / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendHitsPerSecond"
                },
                selection: {
                    mode : 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y.2 hits/sec"
                }
            };
        },
        createGraph: function createGraph() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesHitsPerSecond"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotHitsPerSecond"), dataset, options);
            // setup overview
            $.plot($("#overviewHitsPerSecond"), dataset, prepareOverviewOptions(options));
        }
};

// Hits per second
function refreshHitsPerSecond(fixTimestamps) {
    var infos = hitsPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if (isGraph($("#flotHitsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesHitsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotHitsPerSecond", "#overviewHitsPerSecond");
        $('#footerHitsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var codesPerSecondInfos = {
        data: {"result": {"minY": 0.2, "minX": 1.76831988E12, "maxY": 7.35, "series": [{"data": [[1.76831994E12, 7.35], [1.76831988E12, 0.2]], "isOverall": false, "label": "200", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.76831994E12, "title": "Codes Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendCodesPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "Number of Response Codes %s at %x was %y.2 responses / sec"
                }
            };
        },
    createGraph: function() {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesCodesPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotCodesPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewCodesPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Codes per second
function refreshCodesPerSecond(fixTimestamps) {
    var infos = codesPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotCodesPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesCodesPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotCodesPerSecond", "#overviewCodesPerSecond");
        $('#footerCodesPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var transactionsPerSecondInfos = {
        data: {"result": {"minY": 0.2, "minX": 1.76831988E12, "maxY": 7.35, "series": [{"data": [[1.76831994E12, 7.35], [1.76831988E12, 0.2]], "isOverall": false, "label": "HTTP Request-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.76831994E12, "title": "Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTransactionsPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                }
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTransactionsPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTransactionsPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewTransactionsPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Transactions per second
function refreshTransactionsPerSecond(fixTimestamps) {
    var infos = transactionsPerSecondInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTransactionsPerSecond");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotTransactionsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTransactionsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTransactionsPerSecond", "#overviewTransactionsPerSecond");
        $('#footerTransactionsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var totalTPSInfos = {
        data: {"result": {"minY": 0.2, "minX": 1.76831988E12, "maxY": 7.35, "series": [{"data": [[1.76831994E12, 7.35], [1.76831988E12, 0.2]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.76831994E12, "title": "Total Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTotalTPS"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                },
                colors: ["#9ACD32", "#FF6347"]
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTotalTPS"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTotalTPS"), dataset, options);
        // setup overview
        $.plot($("#overviewTotalTPS"), dataset, prepareOverviewOptions(options));
    }
};

// Total Transactions per second
function refreshTotalTPS(fixTimestamps) {
    var infos = totalTPSInfos;
    // We want to ignore seriesFilter
    prepareSeries(infos.data, false, true);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotTotalTPS"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTotalTPS");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTotalTPS", "#overviewTotalTPS");
        $('#footerTotalTPS .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

// Collapse the graph matching the specified DOM element depending the collapsed
// status
function collapse(elem, collapsed){
    if(collapsed){
        $(elem).parent().find(".fa-chevron-up").removeClass("fa-chevron-up").addClass("fa-chevron-down");
    } else {
        $(elem).parent().find(".fa-chevron-down").removeClass("fa-chevron-down").addClass("fa-chevron-up");
        if (elem.id == "bodyBytesThroughputOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshBytesThroughputOverTime(true);
            }
            document.location.href="#bytesThroughputOverTime";
        } else if (elem.id == "bodyLatenciesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesOverTime(true);
            }
            document.location.href="#latenciesOverTime";
        } else if (elem.id == "bodyCustomGraph") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCustomGraph(true);
            }
            document.location.href="#responseCustomGraph";
        } else if (elem.id == "bodyConnectTimeOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshConnectTimeOverTime(true);
            }
            document.location.href="#connectTimeOverTime";
        } else if (elem.id == "bodyResponseTimePercentilesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimePercentilesOverTime(true);
            }
            document.location.href="#responseTimePercentilesOverTime";
        } else if (elem.id == "bodyResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeDistribution();
            }
            document.location.href="#responseTimeDistribution" ;
        } else if (elem.id == "bodySyntheticResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshSyntheticResponseTimeDistribution();
            }
            document.location.href="#syntheticResponseTimeDistribution" ;
        } else if (elem.id == "bodyActiveThreadsOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshActiveThreadsOverTime(true);
            }
            document.location.href="#activeThreadsOverTime";
        } else if (elem.id == "bodyTimeVsThreads") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTimeVsThreads();
            }
            document.location.href="#timeVsThreads" ;
        } else if (elem.id == "bodyCodesPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCodesPerSecond(true);
            }
            document.location.href="#codesPerSecond";
        } else if (elem.id == "bodyTransactionsPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTransactionsPerSecond(true);
            }
            document.location.href="#transactionsPerSecond";
        } else if (elem.id == "bodyTotalTPS") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTotalTPS(true);
            }
            document.location.href="#totalTPS";
        } else if (elem.id == "bodyResponseTimeVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeVsRequest();
            }
            document.location.href="#responseTimeVsRequest";
        } else if (elem.id == "bodyLatenciesVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesVsRequest();
            }
            document.location.href="#latencyVsRequest";
        }
    }
}

/*
 * Activates or deactivates all series of the specified graph (represented by id parameter)
 * depending on checked argument.
 */
function toggleAll(id, checked){
    var placeholder = document.getElementById(id);

    var cases = $(placeholder).find(':checkbox');
    cases.prop('checked', checked);
    $(cases).parent().children().children().toggleClass("legend-disabled", !checked);

    var choiceContainer;
    if ( id == "choicesBytesThroughputOverTime"){
        choiceContainer = $("#choicesBytesThroughputOverTime");
        refreshBytesThroughputOverTime(false);
    } else if(id == "choicesResponseTimesOverTime"){
        choiceContainer = $("#choicesResponseTimesOverTime");
        refreshResponseTimeOverTime(false);
    }else if(id == "choicesResponseCustomGraph"){
        choiceContainer = $("#choicesResponseCustomGraph");
        refreshCustomGraph(false);
    } else if ( id == "choicesLatenciesOverTime"){
        choiceContainer = $("#choicesLatenciesOverTime");
        refreshLatenciesOverTime(false);
    } else if ( id == "choicesConnectTimeOverTime"){
        choiceContainer = $("#choicesConnectTimeOverTime");
        refreshConnectTimeOverTime(false);
    } else if ( id == "choicesResponseTimePercentilesOverTime"){
        choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        refreshResponseTimePercentilesOverTime(false);
    } else if ( id == "choicesResponseTimePercentiles"){
        choiceContainer = $("#choicesResponseTimePercentiles");
        refreshResponseTimePercentiles();
    } else if(id == "choicesActiveThreadsOverTime"){
        choiceContainer = $("#choicesActiveThreadsOverTime");
        refreshActiveThreadsOverTime(false);
    } else if ( id == "choicesTimeVsThreads"){
        choiceContainer = $("#choicesTimeVsThreads");
        refreshTimeVsThreads();
    } else if ( id == "choicesSyntheticResponseTimeDistribution"){
        choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        refreshSyntheticResponseTimeDistribution();
    } else if ( id == "choicesResponseTimeDistribution"){
        choiceContainer = $("#choicesResponseTimeDistribution");
        refreshResponseTimeDistribution();
    } else if ( id == "choicesHitsPerSecond"){
        choiceContainer = $("#choicesHitsPerSecond");
        refreshHitsPerSecond(false);
    } else if(id == "choicesCodesPerSecond"){
        choiceContainer = $("#choicesCodesPerSecond");
        refreshCodesPerSecond(false);
    } else if ( id == "choicesTransactionsPerSecond"){
        choiceContainer = $("#choicesTransactionsPerSecond");
        refreshTransactionsPerSecond(false);
    } else if ( id == "choicesTotalTPS"){
        choiceContainer = $("#choicesTotalTPS");
        refreshTotalTPS(false);
    } else if ( id == "choicesResponseTimeVsRequest"){
        choiceContainer = $("#choicesResponseTimeVsRequest");
        refreshResponseTimeVsRequest();
    } else if ( id == "choicesLatencyVsRequest"){
        choiceContainer = $("#choicesLatencyVsRequest");
        refreshLatenciesVsRequest();
    }
    var color = checked ? "black" : "#818181";
    if(choiceContainer != null) {
        choiceContainer.find("label").each(function(){
            this.style.color = color;
        });
    }
}

