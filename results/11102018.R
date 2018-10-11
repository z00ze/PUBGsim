colnames(data) <- c("MatchId","IsCustom","EventType","Time","AccountId","X","Y","IsGame")
colnames(zone) <- c("MatchId","IsCustom","Time","IsGame","X","Y","R","nAlive")

data <- subset(data, IsCustom == "false")
zone <- subset(zone, IsCustom == "false")

zone_unique <- zone[!duplicated(zone[c("MatchId", "IsGame")]),]

ggplotly(ggplot(last_events) + 
           geom_point(aes(x = X, y = Y, col = as.factor(IsGame))))

inzone <- function(data, zone){
  
  distances <- sqrt((data$X-zone$X)^2 + (data$Y-zone$Y)^2)

  inside <- sum(distances <= zone$R)
  
  return(inside/zone$nAlive)
}

kokeilu <- subset(data, MatchId == unique(data$MatchId)[1])
last_events <- kokeilu[!duplicated(kokeilu[c("MatchId","AccountId", "IsGame")], fromLast = TRUE),]

for(i in 1:max(kokeilu$IsGame)){
  kuollut <- last_events$AccountId[last_events$EventType == "LogPlayerKill"]
  temp_data <- last_events[last_events$IsGame < i & !(last_events$AccountId %in% kuollut),]
  temp_data <- temp_data[!duplicated(temp_data[c("AccountId")], fromLast = TRUE),]
  # print(unique(temp_data$IsGame))
  print(inzone(temp_data,
         zone_unique[zone_unique$MatchId == unique(last_events$MatchId) & zone_unique$IsGame == i,]))
}
