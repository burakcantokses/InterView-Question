# Müşteri Gruplandırma Uygulaması

## Proje Hakkında

Bu proje, verilen bir metin dosyasındaki 75190 müşteriye ait "borcunu geri ödeyememe olasılıkları" (PD - Probability of Default) verilerini kullanarak bu müşterileri 10 farklı gruba ayırmak için geliştirilmiştir. Oluşturulan gruplar, belirli özelliklere sahip olmalıdır:

- Her bir grubun müşteri sayısı, toplam müşteri sayısının %1'inden az olmamalıdır.
- Her bir grubun ortalama PD değeri, bir önceki grubun ortalama PD değerine çok yakın olmalıdır. (Epsilon = 0.01 hata payına izin verilebilir.)
- Özünde, her iki grup arasındaki PD oranı yaklaşık olarak aynı olmalıdır.

Projeyi çalıştırmak ve bu gruplamayı elde etmek için aşağıdaki adımları takip edebilirsiniz.

## Kullanım

1. Proje kaynak kodlarını bu depodan veya [proje sayfasından](https://github.com/burakcantokses/InterView-Question/tree/master) alın.
2. Metin dosyasındaki müşteri verilerini içeren dosyayı projenin kök dizinine ekleyin.
3. Proje dosyalarını derleyin ve çalıştırın.

Projeyi çalıştırdığınızda, müşterilerin gruplandırılması sonuçlarını elde etmelisiniz.

## Katkı

Eğer bu projeye katkıda bulunmak veya hata bildirmek isterseniz, lütfen bu depoyu çatallayın (fork) ve istediğiniz değişiklikleri yapın. Daha sonra bir çekme isteği (pull request) göndererek değişikliklerinizi bu projeye ekleyebilirsiniz.

---

**English README**

# Customer Segmentation Application

## About the Project

This project has been developed to segment 75190 customers based on their "Probability of Default" (PD) using the data provided in a given text file. The created segments should have specific characteristics:

- The number of customers in each segment should not be less than 1% of the total number of customers.
- The average PD value of each segment should be very close to the average PD value of the previous segment. (An epsilon of 0.01 error margin is acceptable.)
- In essence, the PD ratio between any two segments should be approximately the same.

To run the project and obtain this segmentation, you can follow the steps below.

## Usage

1. Clone the project source code from this repository or the [project page](https://github.com/burakcantokses/InterView-Question/tree/master).
2. Add the file containing customer data from the text file to the root directory of the project.
3. Compile and run the project files.

When you run the project, you should obtain the results of customer segmentation.

## Contribution

If you want to contribute to this project or report a bug, please fork this repository and make the changes you want. Then, you can submit a pull request to add your changes to this project.
