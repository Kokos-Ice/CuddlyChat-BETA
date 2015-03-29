package tools.popup;

import java.util.ArrayList;

    public class Whois {
        ArrayList<TabSite> tabs = new ArrayList<TabSite>();
        int showTab = 0;
        
        public Whois(String header, String headerRight, String tab, String text, String readme, String topRightTitle) {
            this(0);
            tabs.add(new TabSite(header, headerRight, tab, text, readme, topRightTitle));
        }
        
        public Whois(int paramShowTab) {
            this(paramShowTab, "", "", "");
        }

        public Whois(int paramShowTab, String _title, String _topRightTitle, String _tabTitle) {
            this.showTab = paramShowTab;
        }

		public void newTab(String _tabTitle, String _headerTitle, String _message, String readme, String topRightTitle) {
            tabs.add(new TabSite(((TabSite)tabs.get(0)).title, topRightTitle, _tabTitle, _message, readme, topRightTitle));
        }

        public void newTab(String _title, String _topRightTitle, String _tabTitle, String _message, String readme, String topRightTitle) {
            TabSite tab = new TabSite(_title, _topRightTitle, _tabTitle, _message, readme, topRightTitle);
            tabs.add(tab);
        }

        public String getSwitchTab() {
            String switchTab = "";
            StringBuilder sites = new StringBuilder();
            int index = -1;
            
            for (Object objts : tabs) {
            	TabSite ts = (TabSite)objts;
                index++;
                
				sites.append("°>{switchtab}").append(index).append("<°");
 				sites.append("°1>{table|0|w1|w1<>{tc}<>layout/boxb_tl.png<>layout/boxb_tc...w_0.xrepeat.gif<° ");
 				sites.append("°>{tc}<>RIGHT<>{noxrep}<>layout/boxb_tr.png<°");
 				sites.append("°>{endtable}<>LEFT<°#°+8003°");
 				sites.append("°>{table|4|w1,bgimg;layout/boxb_cl.gif;layout/boxb_cc.gif;layout/boxb_cr.gif|4<>{tc}<°");
 				sites.append("°11>RIGHT<°").append(ts.topRightTitle).append("#°+6005° _#°+9007°°20>LEFT<+0010°").append(ts.title).append("_°r12°").append(ts.readme).append("#°%00°##°>{endtable}<>LEFT<°#°+8002°");
 				sites.append("°1>{table|0|w1|w1<>{tc}<>layout/boxb_bl-f.png<>layout/boxb_bc-f...w_0.xrepeat.png<° ");
 				sites.append("°>{tc}<>RIGHT<>{noxrep}<>layout/boxb_br-f.png<°");
 				sites.append("°>{endtable}<>LEFT<°#°+8004°°+9005°_");
 				sites.append("°BB12>{table|w7|w4<>layout/line_l.png<°");
 				sites.append(activeTab(index));
 				sites.append("°>layout/line_c...w_0.xrepeat.png<° °+6010>{tc}<>RIGHT<>{noxrep}<°");
 				sites.append("°>layout/line_r.png<+6010>{endtable}<>LEFT<°_°°# #°>Center<°").append("#°>Left<°§");
 				sites.append(ts.message);
            }
            
            sites.append("°>{showtab}").append(this.showTab).append("<°");
            switchTab = sites.toString();
            return switchTab;
        }

        private String activeTab(int index) {
            StringBuilder strTabs = new StringBuilder();
            int ind = -1;
            
            for (Object objts : tabs) {
                TabSite ts = (TabSite)objts;
                ind++;
                String strTab = "°>layout/tab_i_l...w_8.mx_-1.png<>layout/tab_i_c...w_0.xrepeat.png<>_h" + ts.tabTitle + "|/tp-showtab " + ind + "<>{noxrep}<>layout/tab_i_r.png<°";
                if (ind == index)
                    strTab = "°>layout/tab_a_l...w_8.mx_-1.png<>layout/tab_a_c...w_0.xrepeat.png<>_h" + ts.tabTitle + "|/tp-showtab " + ind + "<>{noxrep}<>layout/tab_a_r.png<°";
                strTabs.append(strTab);
            }
            
            return strTabs.toString();
        }

        private class TabSite {
            public String title;
            public String topRightTitle;
            public String tabTitle;
            public String message;
            public String readme;

            public TabSite(String _title, String _topRightTitle, String _tabTitle, String _message, String readme, String topRightTitle2) {
                this.title = _title;
                this.topRightTitle = _topRightTitle;
                this.tabTitle = _tabTitle;
                this.message = _message;
                this.readme = readme;
                this.topRightTitle = topRightTitle2;
            }
        }
}
