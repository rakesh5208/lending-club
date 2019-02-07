package org.learningwithrakesh.lendingclubapi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.learningwithrakesh.lendingclubapi.entity.Member;
import org.learningwithrakesh.lendingclubapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.Environment;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = { "org.learningwithrakesh.lendingclubapi" })
public class App {
	@Autowired
	MemberService memberService;

	@Autowired
	Environment env;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}

	public App() {

	}

	@PostConstruct
	public void setupData() {
		if (env.getProperty("app.db_dumb.init").toLowerCase().equals("true")) {
			File file = new File(env.getProperty("app.db_dumb_csv.path"));
			String line = "";
			try {
				FileReader fs = new FileReader(file);
				BufferedReader br = new BufferedReader(fs);
				boolean skipFirstLine = true;
				while ((line = br.readLine()) != null) {
					if (!skipFirstLine) {
						Member member = this.createMember(line);
						if (member != null) {
							this.memberService.save(member);
						}
					} else {
						skipFirstLine = false;
					}
				}
			} catch (Exception ex) {

				ex.printStackTrace();

			}
		}

	}

	private String[] split(String line) {
		List<String> splits = new ArrayList<String>();
		int startPos = 0;
		int endPos = 0;
		try {
			while (line != null && line.indexOf(",") != -1) {
				if (line.indexOf("\"") == -1 || (line.indexOf(",") < line.indexOf("\""))) {
					endPos = line.indexOf(",");
					splits.add(line.substring(0, endPos).trim());
					line = line.substring(endPos + 1);
				} else {
					startPos = line.indexOf("\"") + 1;
					StringBuffer sb = new StringBuffer();
					for (int i = startPos;;) {
						if (line.charAt(i) == '\"') {
							line = line.substring(i + 2);
							break;
						}
						sb.append(line.charAt(i));
						i++;
					}
					splits.add(sb.toString().trim());
				}
			}
			// probalbly last cols
			if (line.length() > 0) {
				splits.add(line);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return splits.toArray(new String[splits.size()]);
	}

	private Member createMember(String row) {
		String[] cols = this.split(row);
		if (cols.length != 20) {
			return null;
		} else {
			Member member = new Member();
			try {
				member.setId(Long.parseLong(cols[0]));
				member.setLoadAmnt(Double.parseDouble(cols[1]));
				member.setFundedAmntInv(Double.parseDouble(cols[2]));
				member.setTerm(cols[3]);
				member.setIntRate(Double.parseDouble(cols[4]));
				member.setInstallment(Double.parseDouble(cols[5]));
				member.setGrade(cols[6].charAt(0));
				member.setEmpTitle(cols[7]);
				member.setEmpLength(cols[8]);
				member.setHomeOwnership(cols[9]);
				member.setAnnualInc(Double.parseDouble(cols[10]));
				member.setVerificationStatus(cols[11]);
				member.setIssueDate(cols[12]);
				member.setLoadStatus(cols[13]);
				member.setDescription(cols[14]);
				member.setPurpose(cols[15]);
				member.setTitle(cols[16]);
				member.setAddrState(cols[17]);
				member.setLastPymntDate(cols[18]);
				member.setLastPymntAmnt(Double.parseDouble(cols[19]));
				return member;
			} catch (Exception ex) {
				// skip any parser error
				return null;
			}

		}

	}
}
