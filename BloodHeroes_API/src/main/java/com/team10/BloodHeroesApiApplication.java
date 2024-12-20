package com.team10;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.team10.entity.ROLE;
import com.team10.entity.StateAndUTs;
import com.team10.entity.User;
import com.team10.repository.StateAndUTsRepository;
import com.team10.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class BloodHeroesApiApplication {

	private final StateAndUTsRepository stateAndUTsRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(BloodHeroesApiApplication.class, args);
	}

	@PostConstruct
	public void makeSuperAdmin() {
		if (userRepository.findAll().size() > 0)
			return;
		//@formatter:off
			userRepository.save(User.builder()
					.name("SuperAdmin")
					.email("mdasadali789@gmail.com")
					.password(encoder.encode("admin123"))
					.contacts("7586090356")
					.role(ROLE.ADMIN)
					.isActivated(true)
					.build());
		//@formatter:on
	}

	@PostConstruct
	public void init() {
		// Clear existing data
		if (stateAndUTsRepository.findAll().size() > 0)
			return;
		stateAndUTsRepository.deleteAll();

		// Create and save states and union territories with their districts
		stateAndUTsRepository.saveAll(Arrays.asList(
				StateAndUTs.builder().name("Andaman & Nicobar Islands")
						.districts(Arrays.asList("Nicobars", "North & Middle Andaman", "South Andaman")).build(),
				StateAndUTs.builder().name("Andhra Pradesh")
						.districts(Arrays.asList("Anantapur", "Chittoor", "East Godavari", "Guntur", "Krishna",
								"Kurnool", "Prakasam", "Srikakulam", "Visakhapatnam", "Vizianagaram", "West Godavari",
								"YSR Kadapa"))
						.build(),
				StateAndUTs.builder().name("Arunachal Pradesh")
						.districts(Arrays.asList("Anjaw", "Changlang", "Dibang Valley", "East Kameng", "East Siang",
								"Kamle", "Kra Daadi", "Kurung Kumey", "Lepa Rada", "Lohit", "Longding",
								"Lower Dibang Valley", "Lower Siang", "Lower Subansiri", "Namsai", "Pakke Kessang",
								"Papum Pare", "Shi Yomi", "Siang", "Tawang", "Tirap", "Upper Siang", "Upper Subansiri",
								"West Kameng", "West Siang"))
						.build(),
				StateAndUTs.builder().name("Assam")
						.districts(Arrays.asList("Baksa", "Barpeta", "Biswanath", "Bongaigaon", "Cachar", "Charaideo",
								"Chirang", "Darrang", "Dhemaji", "Dhubri", "Dibrugarh", "Dima Hasao", "Goalpara",
								"Golaghat", "Hailakandi", "Hojai", "Jorhat", "Kamrup", "Kamrup Metropolitan",
								"Karbi Anglong", "Karimganj", "Kokrajhar", "Lakhimpur", "Majuli", "Morigaon", "Nagaon",
								"Nalbari", "Sivasagar", "Sonitpur", "South Salmara-Mankachar", "Tinsukia", "Udalguri",
								"West Karbi Anglong"))
						.build(),
				StateAndUTs.builder().name("Bihar")
						.districts(Arrays.asList("Araria", "Arwal", "Aurangabad", "Banka", "Begusarai", "Bhagalpur",
								"Bhojpur", "Buxar", "Darbhanga", "East Champaran", "Gaya", "Gopalganj", "Jamui",
								"Jehanabad", "Kaimur", "Katihar", "Khagaria", "Kishanganj", "Lakhisarai", "Madhepura",
								"Madhubani", "Munger", "Muzaffarpur", "Nalanda", "Nawada", "Patna", "Purnia", "Rohtas",
								"Saharsa", "Samastipur", "Saran", "Sheikhpura", "Sheohar", "Sitamarhi", "Siwan",
								"Supaul", "Vaishali", "West Champaran"))
						.build(),
				StateAndUTs.builder().name("Chandigarh").districts(Arrays.asList("Chandigarh")).build(),
				StateAndUTs.builder().name("Chhattisgarh")
						.districts(Arrays.asList("Balod", "Baloda Bazar", "Balrampur", "Bastar", "Bemetara", "Bijapur",
								"Bilaspur", "Dantewada", "Dhamtari", "Durg", "Gariaband", "Gaurela Pendra Marwahi",
								"Janjgir Champa", "Jashpur", "Kabirdham", "Kanker", "Kondagaon", "Korba", "Koriya",
								"Mahasamund", "Mungeli", "Narayanpur", "Raigarh", "Raipur", "Rajnandgaon", "Sukma",
								"Surajpur", "Surguja"))
						.build(),
				StateAndUTs.builder().name("Dadra & Nagar Haveli").districts(Arrays.asList("Dadra & Nagar Haveli"))
						.build(),
				StateAndUTs.builder().name("Daman & Diu").districts(Arrays.asList("Daman", "Diu")).build(),
				StateAndUTs.builder().name("Delhi")
						.districts(Arrays.asList("Central Delhi", "East Delhi", "New Delhi", "North Delhi",
								"North East Delhi", "North West Delhi", "Shahdara", "South Delhi", "South East Delhi",
								"South West Delhi", "West Delhi"))
						.build(),
				StateAndUTs.builder().name("Goa").districts(Arrays.asList("North Goa", "South Goa")).build(),
				StateAndUTs.builder().name("Gujarat")
						.districts(Arrays.asList("Ahmedabad", "Amreli", "Anand", "Aravalli", "Banaskantha", "Bharuch",
								"Bhavnagar", "Botad", "Chhota Udaipur", "Dahod", "Dang", "Devbhoomi Dwarka",
								"Gandhinagar", "Gir Somnath", "Jamnagar", "Junagadh", "Kheda", "Kutch", "Mahisagar",
								"Mehsana", "Morbi", "Narmada", "Navsari", "Panchmahal", "Patan", "Porbandar", "Rajkot",
								"Sabarkantha", "Surat", "Surendranagar", "Tapi", "Vadodara", "Valsad"))
						.build(),
				StateAndUTs.builder().name("Haryana")
						.districts(Arrays.asList("Ambala", "Bhiwani", "Charkhi Dadri", "Faridabad", "Fatehabad",
								"Gurugram", "Hisar", "Jhajjar", "Jind", "Kaithal", "Karnal", "Kurukshetra",
								"Mahendragarh", "Nuh", "Palwal", "Panchkula", "Panipat", "Rewari", "Rohtak", "Sirsa",
								"Sonipat", "Yamunanagar"))
						.build(),
				StateAndUTs.builder().name("Himachal Pradesh")
						.districts(Arrays.asList("Bilaspur", "Chamba", "Hamirpur", "Kangra", "Kinnaur", "Kullu",
								"Lahaul & Spiti", "Mandi", "Shimla", "Sirmaur", "Solan", "Una"))
						.build(),
				StateAndUTs.builder().name("Jammu and Kashmir")
						.districts(Arrays.asList("Anantnag", "Bandipora", "Baramulla", "Budgam", "Doda", "Ganderbal",
								"Jammu", "Kathua", "Kishtwar", "Kulgam", "Kupwara", "Poonch", "Pulwama", "Rajouri",
								"Ramban", "Reasi", "Samba", "Shopian", "Srinagar", "Udhampur"))
						.build(),
				StateAndUTs.builder().name("Jharkhand")
						.districts(Arrays.asList("Bokaro", "Chatra", "Deoghar", "Dhanbad", "Dumka", "East Singhbhum",
								"Garhwa", "Giridih", "Godda", "Gumla", "Hazaribagh", "Jamtara", "Khunti", "Koderma",
								"Latehar", "Lohardaga", "Pakur", "Palamu", "Ramgarh", "Ranchi", "Sahibganj",
								"Seraikela-Kharsawan", "Simdega", "West Singhbhum"))
						.build(),
				StateAndUTs.builder().name("Karnataka")
						.districts(Arrays.asList("Bagalkot", "Ballari", "Belagavi", "Bengaluru Rural",
								"Bengaluru Urban", "Bidar", "Chamarajanagar", "Chikkaballapur", "Chikkamagaluru",
								"Chitradurga", "Dakshina Kannada", "Davanagere", "Dharwad", "Gadag", "Hassan", "Haveri",
								"Kalaburagi", "Kodagu", "Kolar", "Koppal", "Mandya", "Mysuru", "Raichur", "Ramanagara",
								"Shivamogga", "Tumakuru", "Udupi", "Uttara Kannada", "Vijayapura", "Yadgir"))
						.build(),
				StateAndUTs.builder().name("Kerala")
						.districts(Arrays.asList("Alappuzha", "Ernakulam", "Idukki", "Kannur", "Kasaragod", "Kollam",
								"Kottayam", "Kozhikode", "Malappuram", "Palakkad", "Pathanamthitta",
								"Thiruvananthapuram", "Thrissur", "Wayanad"))
						.build(),
				StateAndUTs.builder().name("Ladakh").districts(Arrays.asList("Kargil", "Leh")).build(),
				StateAndUTs.builder().name("Lakshadweep").districts(Arrays.asList("Lakshadweep")).build(),
				StateAndUTs.builder().name("Madhya Pradesh")
						.districts(Arrays.asList("Agar Malwa", "Alirajpur", "Anuppur", "Ashoknagar", "Balaghat",
								"Barwani", "Betul", "Bhind", "Bhopal", "Burhanpur", "Chhatarpur", "Chhindwara", "Damoh",
								"Datia", "Dewas", "Dhar", "Dindori", "Guna", "Gwalior", "Harda", "Hoshangabad",
								"Indore", "Jabalpur", "Jhabua", "Katni", "Khandwa", "Khargone", "Mandla", "Mandsaur",
								"Morena", "Narsinghpur", "Neemuch", "Niwari", "Panna", "Raisen", "Rajgarh", "Ratlam",
								"Rewa", "Sagar", "Satna", "Sehore", "Seoni", "Shahdol", "Shajapur", "Sheopur",
								"Shivpuri", "Sidhi", "Singrauli", "Tikamgarh", "Ujjain", "Umaria", "Vidisha"))
						.build(),
				StateAndUTs.builder().name("Maharashtra")
						.districts(Arrays.asList("Ahmednagar", "Akola", "Amravati", "Aurangabad", "Beed", "Bhandara",
								"Buldhana", "Chandrapur", "Dhule", "Gadchiroli", "Gondia", "Hingoli", "Jalgaon",
								"Jalna", "Kolhapur", "Latur", "Mumbai City", "Mumbai Suburban", "Nagpur", "Nanded",
								"Nandurbar", "Nashik", "Osmanabad", "Palghar", "Parbhani", "Pune", "Raigad",
								"Ratnagiri", "Sangli", "Satara", "Sindhudurg", "Solapur", "Thane", "Wardha", "Washim",
								"Yavatmal"))
						.build(),
				StateAndUTs.builder().name("Manipur")
						.districts(Arrays.asList("Bishnupur", "Chandel", "Churachandpur", "Imphal East", "Imphal West",
								"Jiribam", "Kakching", "Kamjong", "Kangpokpi", "Noney", "Pherzawl", "Senapati",
								"Tamenglong", "Tengnoupal", "Thoubal", "Ukhrul"))
						.build(),
				StateAndUTs.builder().name("Meghalaya")
						.districts(Arrays.asList("East Garo Hills", "East Jaintia Hills", "East Khasi Hills",
								"North Garo Hills", "Ri Bhoi", "South Garo Hills", "South West Garo Hills",
								"South West Khasi Hills", "West Garo Hills", "West Jaintia Hills", "West Khasi Hills"))
						.build(),
				StateAndUTs.builder().name("Mizoram")
						.districts(Arrays.asList("Aizawl", "Champhai", "Hnahthial", "Khawzawl", "Kolasib", "Lawngtlai",
								"Lunglei", "Mamit", "Saiha", "Saitual", "Serchhip"))
						.build(),
				StateAndUTs.builder().name("Nagaland")
						.districts(Arrays.asList("Chumukedima", "Dimapur", "Kiphire", "Kohima", "Longleng",
								"Mokokchung", "Mon", "Noklak", "Peren", "Phek", "Tuensang", "Wokha", "Zunheboto"))
						.build(),
				StateAndUTs.builder().name("Odisha")
						.districts(Arrays.asList("Angul", "Balangir", "Balasore", "Bargarh", "Bhadrak", "Boudh",
								"Cuttack", "Debagarh", "Dhenkanal", "Gajapati", "Ganjam", "Jagatsinghapur", "Jajpur",
								"Jharsuguda", "Kalahandi", "Kandhamal", "Kendrapara", "Kendujhar", "Khordha", "Koraput",
								"Malkangiri", "Mayurbhanj", "Nabarangpur", "Nayagarh", "Nuapada", "Puri", "Rayagada",
								"Sambalpur", "Sonepur", "Sundergarh"))
						.build(),
				StateAndUTs.builder().name("Puducherry")
						.districts(Arrays.asList("Karaikal", "Mahe", "Puducherry", "Yanam")).build(),
				StateAndUTs.builder().name("Punjab")
						.districts(Arrays.asList("Amritsar", "Barnala", "Bathinda", "Faridkot", "Fatehgarh Sahib",
								"Fazilka", "Ferozepur", "Gurdaspur", "Hoshiarpur", "Jalandhar", "Kapurthala",
								"Ludhiana", "Malerkotla", "Mansa", "Moga", "Mohali", "Muktsar", "Pathankot", "Patiala",
								"Rupnagar", "Sangrur", "Shaheed Bhagat Singh Nagar", "Tarn Taran"))
						.build(),
				StateAndUTs.builder().name("Rajasthan")
						.districts(Arrays.asList("Ajmer", "Alwar", "Banswara", "Baran", "Barmer", "Bharatpur",
								"Bhilwara", "Bikaner", "Bundi", "Chittorgarh", "Churu", "Dausa", "Dholpur", "Dungarpur",
								"Hanumangarh", "Jaipur", "Jaisalmer", "Jalore", "Jhalawar", "Jhunjhunu", "Jodhpur",
								"Karauli", "Kota", "Nagaur", "Pali", "Pratapgarh", "Rajsamand", "Sawai Madhopur",
								"Sikar", "Sirohi", "Sri Ganganagar", "Tonk", "Udaipur"))
						.build(),
				StateAndUTs.builder().name("Sikkim")
						.districts(Arrays.asList("East Sikkim", "North Sikkim", "South Sikkim", "West Sikkim")).build(),
				StateAndUTs.builder().name("Tamil Nadu")
						.districts(Arrays.asList("Ariyalur", "Chengalpattu", "Chennai", "Coimbatore", "Cuddalore",
								"Dharmapuri", "Dindigul", "Erode", "Kallakurichi", "Kancheepuram", "Kanyakumari",
								"Karur", "Krishnagiri", "Madurai", "Mayiladuthurai", "Nagapattinam", "Namakkal",
								"Nilgiris", "Perambalur", "Pudukkottai", "Ramanathapuram", "Ranipet", "Salem",
								"Sivaganga", "Tenkasi", "Thanjavur", "Theni", "Thoothukudi", "Tiruchirappalli",
								"Tirunelveli", "Tirupattur", "Tiruppur", "Tiruvallur", "Tiruvannamalai", "Tiruvarur",
								"Vellore", "Viluppuram", "Virudhunagar"))
						.build(),
				StateAndUTs.builder().name("Telangana").districts(Arrays.asList("Adilabad", "Bhadradri Kothagudem",
						"Hyderabad", "Jagtial", "Jangaon", "Jayashankar Bhupalapally", "Jogulamba Gadwal", "Kamareddy",
						"Karimnagar", "Khammam", "Komaram Bheem", "Mahabubabad", "Mahabubnagar", "Mancherial", "Medak",
						"Medchal Malkajgiri", "Mulugu", "Nagarkurnool", "Nalgonda", "Narayanpet", "Nirmal", "Nizamabad",
						"Peddapalli", "Rajanna Sircilla", "Rangareddy", "Sangareddy", "Siddipet", "Suryapet",
						"Vikarabad", "Wanaparthy", "Warangal Rural", "Warangal Urban", "Yadadri Bhuvanagiri")).build(),
				StateAndUTs.builder().name("Tripura")
						.districts(Arrays.asList("Dhalai", "Gomati", "Khowai", "North Tripura", "Sepahijala",
								"South Tripura", "Unakoti", "West Tripura"))
						.build(),
				StateAndUTs.builder().name("Uttar Pradesh").districts(Arrays.asList("Agra", "Aligarh", "Ambedkar Nagar",
						"Amethi", "Amroha", "Auraiya", "Ayodhya", "Azamgarh", "Baghpat", "Bahraich", "Ballia",
						"Balrampur", "Banda", "Barabanki", "Bareilly", "Basti", "Bhadohi", "Bijnor", "Budaun",
						"Bulandshahr", "Chandauli", "Chitrakoot", "Deoria", "Etah", "Etawah", "Farrukhabad", "Fatehpur",
						"Firozabad", "Gautam Buddha Nagar", "Ghaziabad", "Ghazipur", "Gonda", "Gorakhpur", "Hamirpur",
						"Hapur", "Hardoi", "Hathras", "Jalaun", "Jaunpur", "Jhansi", "Kannauj", "Kanpur Dehat",
						"Kanpur Nagar", "Kasganj", "Kaushambi", "Kushinagar", "Lakhimpur Kheri", "Lalitpur", "Lucknow",
						"Maharajganj", "Mahoba", "Mainpuri", "Mathura", "Mau", "Meerut", "Mirzapur", "Moradabad",
						"Muzaffarnagar", "Pilibhit", "Pratapgarh", "Prayagraj", "Rae Bareli", "Rampur", "Saharanpur",
						"Sambhal", "Sant Kabir Nagar", "Shahjahanpur", "Shamli", "Shravasti", "Siddharthnagar",
						"Sitapur", "Sonbhadra", "Sultanpur", "Unnao", "Varanasi")).build(),
				StateAndUTs.builder().name("Uttarakhand")
						.districts(Arrays.asList("Almora", "Bageshwar", "Chamoli", "Champawat", "Dehradun", "Haridwar",
								"Nainital", "Pauri Garhwal", "Pithoragarh", "Rudraprayag", "Tehri Garhwal",
								"Udham Singh Nagar", "Uttarkashi"))
						.build(),
				StateAndUTs.builder().name("West Bengal")
						.districts(Arrays.asList("Alipurduar", "Bankura", "Birbhum", "Cooch Behar", "Dakshin Dinajpur",
								"Darjeeling", "Hooghly", "Howrah", "Jalpaiguri", "Jhargram", "Kalimpong", "Kolkata",
								"Malda", "Murshidabad", "Nadia", "North 24 Parganas", "Paschim Bardhaman",
								"Paschim Medinipur", "Purba Bardhaman", "Purba Medinipur", "Purulia",
								"South 24 Parganas", "Uttar Dinajpur"))
						.build()));
	}

//	@PostConstruct
//	public void secretKeyGenerator() throws NoSuchAlgorithmException, InvalidKeySpecException {
//		String orginalString="BloodHeroesTeam10AECIT2025";
//		byte[] salt=new byte[64];
//		PBEKeySpec spec = new PBEKeySpec(orginalString.toCharArray(),salt,10,512);
//		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
//		byte[] encoded = factory.generateSecret(spec).getEncoded();
//		String encodeToString = Base64.getEncoder().encodeToString(encoded);
//		System.out.println(encodeToString);
//	}

}
